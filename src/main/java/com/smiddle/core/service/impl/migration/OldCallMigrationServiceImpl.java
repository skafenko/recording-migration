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
import com.smiddle.rec.model.calls.CallType;
import com.smiddle.rec.model.calls.ParticipantType;
import com.smiddle.rec.model.calls.utility.CallTrackPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(4)
@RequiredArgsConstructor
@Slf4j
public class OldCallMigrationServiceImpl implements MigrationService {
    private static final int DEFAULT_LIMIT_CALLS = 1;
    private static final String DEFAULT_CODEC = "WAVE_FORMAT_MULAW/8000";
    private final OldCallDAO oldCallDAO;
    private final CallDAO callDAO;

    @Override
    public void migrate() {
        log.info("STARTED calls migration");
        int offset = 0;
        long count = oldCallDAO.getCount();
//        while (count > offset) {
        oldCallDAO.getAllOldCalls(DEFAULT_LIMIT_CALLS, offset).stream()
                .map(this::migrateOldCallToCall)
                .forEach(callDAO::saveCall);
//            offset += DEFAULT_LIMIT_USERS;
//        }
        log.info("FINISHED calls migration");
    }

    private Call migrateOldCallToCall(OldCall oldCall) {
        String trackPath = encryptTrackPath(oldCall.getTrackPath(), oldCall.getDateStart(), oldCall.getLicenseNumber());
        Call call = Call.builder().id(oldCall.getId())
                .ccid(oldCall.getCcid())
                .contentType(CallContentType.AUDIO)
                .crmCallId(oldCall.getCrmCallId())
                .finishState(oldCall.getFinishState())
                .dateReg(oldCall.getDateReg())
                .dateStart(oldCall.getDateStart())
                .direction(oldCall.getDirection())
                .duration(oldCall.getDuration())
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
        call.setParts(Collections.singletonList(generateCallPart(oldCall, trackPath)));
        return call;
    }

    private CallParts generateCallPart(OldCall oldCall, String trackPath) {
        // TODO: 29.10.2019 fix duplicate call parts
        CallParts callParts = CallParts.builder()
                .callType(CallType.NORMAL)
                .duration(oldCall.getDuration())
                .finishState(oldCall.getFinishState())
                .trackPath(trackPath)
                .partId(1)
                .startTime(oldCall.getDateStart())
                .call(Call.builder().id(oldCall.getId()).build())
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
                .id(participant.getId())
                .agentId(participant.getAgentId())
                .codec(DEFAULT_CODEC)
                .date(participant.getDate())
                .duration(participant.getDuration())
                .finishState(callParts.getFinishState())
                .participantType(ParticipantType.UNDEFINED)
                .phone(participant.getPhone())
                .trackPath(callParts.getTrackPath())
                .xRefCi(participant.getXRefCi())
                .call(Call.builder().id(participant.getCall().getId()).build())
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
