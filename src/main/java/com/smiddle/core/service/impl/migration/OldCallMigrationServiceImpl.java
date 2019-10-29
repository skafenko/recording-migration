package com.smiddle.core.service.impl.migration;

import com.smiddle.core.dao.CallDAO;
import com.smiddle.core.dao.old.OldCallDAO;
import com.smiddle.core.model.Call;
import com.smiddle.core.model.User;
import com.smiddle.core.model.extra.CallParticipant;
import com.smiddle.core.model.extra.CallParts;
import com.smiddle.core.model.extra.CallStatistic;
import com.smiddle.core.model.old.OldCall;
import com.smiddle.core.model.old.OldUser;
import com.smiddle.core.model.old.extra.OldCallParticipant;
import com.smiddle.core.service.MigrationService;
import com.smiddle.rec.model.calls.CallContentType;
import com.smiddle.rec.model.calls.CallParticipantType;
import com.smiddle.rec.model.calls.CallType;
import com.smiddle.rec.model.calls.ParticipantType;
import com.smiddle.rec.model.calls.utility.CallTrackPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Order(4)
@RequiredArgsConstructor
@Slf4j
public class OldCallMigrationServiceImpl implements MigrationService {
    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
    @Value("${default.limit.calls:100}")
    private int defaultLimitCalls;
    @Value("${default.codec:WAVE_FORMAT_MULAW/8000}")
    private String defaultCodec;
    private final OldCallDAO oldCallDAO;
    private final CallDAO callDAO;

    @Override
    public void migrate() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("To start calls migration enter start date in format (" + PATTERN + ") or exit to EXIT?");
            Date startDate = readDate(scanner);
            System.out.println("To start calls migration enter finish date in format (" + PATTERN + ") or exit to EXIT?");
            Date finishDate = readDate(scanner);
            log.info("STARTED calls migration from '{}' to '{}'", startDate, finishDate);
            int offset = 0;
            long count = oldCallDAO.getCount(startDate, finishDate);
            log.info("Found {} calls to migrate", count);
            while (count > offset) {
                List<OldCall> oldCalls = oldCallDAO.getAllOldCalls(startDate, finishDate, defaultLimitCalls, offset);
                oldCalls.stream()
                        .map(this::migrateOldCallToCall)
                        .forEach(this::saveCall);
                offset += defaultLimitCalls;
                log.info("Migrated {} calls", oldCalls.size());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("FINISHED calls migration");
        }
    }

    private void saveCall(Call call) {
        try {
            callDAO.saveCall(call);
        } catch (Exception e) {
            log.error("Unable to save Call wit id {}, ex={}", call.getId(), e.getMessage());
        }
    }

    private Date readDate(Scanner scanner) throws ParseException {
        String command = scanner.nextLine().trim();
        if ("exit".equalsIgnoreCase(command)) {
            throw new RuntimeException("Exit was pressed");
        }
        if (command.length() > 0) {
            return dateFormat.parse(command);
        }
        throw new RuntimeException("Command length is less than 0");
    }

    private Call migrateOldCallToCall(OldCall oldCall) {
        String trackPath = encryptTrackPath(oldCall.getTrackPath(), oldCall.getDateStart(), oldCall.getLicenseNumber());
        Call call = Call.builder()
                .rid(UUID.randomUUID().toString())
                .ccid(oldCall.getCcid())
                .contentType(CallContentType.AUDIO)
                .crmCallId(oldCall.getCrmCallId())
                .finishState(oldCall.getFinishState())
                .dateReg(oldCall.getDateReg())
                .dateStart(oldCall.getDateStart())
                .direction(oldCall.getDirection())
                .duration(oldCall.getDuration())
                .callParticipantType(CallParticipantType.UNDEFINED)
                .finishState(oldCall.getFinishState())
                .licenseNumber(oldCall.getLicenseNumber())
                .licensed(oldCall.getLicensed())
                .mapped(oldCall.isMapped())
                .mp4Link(oldCall.getMp4Link())
                .msRemoved(oldCall.isMsRemoved())
                .msSessionId(oldCall.getMsSessionId())
                .rtspLink(oldCall.getRtspLink())
                .trackPath(trackPath)
                .callStatistic(getDefaultCallStatistic())
                .build();
        call.setParts(Collections.singletonList(generateCallPart(oldCall, trackPath, call)));
        return call;
    }

    private CallParts generateCallPart(OldCall oldCall, String trackPath, Call call) {
        CallParts callParts = CallParts.builder()
                .rid(UUID.randomUUID().toString())
                .callType(CallType.NORMAL)
                .duration(oldCall.getDuration())
                .finishState(oldCall.getFinishState())
                .trackPath(trackPath)
                .streamPath1(trackPath)
                .streamPath2(trackPath)
                .partId(1)
                .startTime(oldCall.getDateStart())
                .call(call)
                .build();
        callParts.setParticipants(generateCallParticipants(oldCall, callParts));
        return callParts;
    }

    private List<CallParticipant> generateCallParticipants(OldCall oldCall, CallParts callParts) {
        return oldCall.getParticipants().stream()
                .map(oldCallParticipant -> mapOldParticipantToParticipant(oldCallParticipant, callParts))
                .collect(Collectors.toList());
    }

    private CallParticipant mapOldParticipantToParticipant(OldCallParticipant participant, CallParts callParts) {
        return CallParticipant.builder()
                .rid(UUID.randomUUID().toString())
                .agentId(participant.getAgentId())
                .codec(defaultCodec)
                .date(participant.getDate())
                .duration(participant.getDuration())
                .finishState(callParts.getFinishState())
                .participantType(ParticipantType.UNDEFINED)
                .phone(participant.getPhone())
                .trackPath(callParts.getTrackPath())
                .xRefCi(participant.getXRefCi())
                .call(callParts.getCall())
                .callPart(callParts)
                .user(getUser(participant.getUser()))
                .build();
    }

    private User getUser(OldUser oldUser) {
        return oldUser == null ? null : User.builder().id(oldUser.getId()).build();
    }

    private CallStatistic getDefaultCallStatistic() {
        return CallStatistic.builder().partsCount(1).build();
    }

    private String encryptTrackPath(String trackPath, Date dateStart, int licenseNumber) {
        CallTrackPath callPartTrackPath = new CallTrackPath(trackPath, licenseNumber, dateStart);
        return callPartTrackPath.getEncryptedTrackPath();
    }
}
