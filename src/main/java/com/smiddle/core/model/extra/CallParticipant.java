package com.smiddle.core.model.extra;

import com.smiddle.common.model.RidGenerator;
import com.smiddle.core.model.Call;
import com.smiddle.core.model.User;
import com.smiddle.rec.model.calls.CallState;
import com.smiddle.rec.model.calls.ParticipantType;
import com.smiddle.rec.model.calls.utility.State;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REC_CALL_PARTICIPANTS")
@Data
@EqualsAndHashCode(of = {"agentId", "phone", "xRefCi", "date", "duration"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallParticipant implements State {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    protected volatile Long id;
    @GeneratorType(type = RidGenerator.class, when = GenerationTime.INSERT)
    @Column(name = "RID")
    protected volatile String rid;
    @Column(name = "AGENT_ID")
    private String agentId;
    @Column(name = "PHONE", nullable = false)
    private String phone;
    @Column(name = "XREFCI", nullable = false)
    private String xRefCi;
    @Column(name = "TALKING_DATE", nullable = false)
    private Date date;
    @Column(name = "DURATION", nullable = false)
    private long duration;
    @Column(name = "CODEC", length = 60)
    private String codec;
    @Column(name = "TRACK_PATH", length = 1024)
    private String trackPath;
    @Column(name = "RECORDER")
    private String recorder;
    @Enumerated(EnumType.STRING)
    @Column(name = "FINISH_STATE")
    private volatile CallState finishState;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNDEFINED'")
    @Column(name = "PARTICIPANT_TYPE", nullable = false)
    private ParticipantType participantType;
    @ManyToOne
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTICIPANTS_ADM_USERS"))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALL_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTICIPANTS_REC_CALLS"))
    private Call call;
    @ManyToOne
    @JoinColumn(name = "CALL_PART_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTICIPANTS_CALL_PART"))
    private CallParts callPart;
    @Column(name = "MESSENGER_TYPE")
    private String messengerType;

    @Override
    public CallState state() {
        return finishState;
    }
}
