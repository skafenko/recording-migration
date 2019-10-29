package com.smiddle.core.model.extra;

import com.smiddle.common.model.BaseEntity;
import com.smiddle.core.model.Call;
import com.smiddle.rec.model.calls.CallState;
import com.smiddle.rec.model.calls.CallType;
import com.smiddle.rec.model.calls.utility.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "REC_CALL_PARTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallParts extends BaseEntity implements State {
    public static final long serialVersionUID = -1L;
    @Column(name = "RID")
    protected volatile String rid;
    @Column(name = "PART")
    private int partId;
    @Column(name = "TRACK_PATH")
    private String trackPath;
    @Column(name = "STREAM_PATH1")
    private String streamPath1;
    @Column(name = "STREAM_PATH2")
    private String streamPath2;
    @Enumerated(EnumType.STRING)
    @Column(name = "FINISH_STATE", nullable = false)
    private CallState finishState;
    @Enumerated(EnumType.STRING)
    @Column(name = "CALL_TYPE", nullable = false)
    private CallType callType;
    @Column(name = "START_TIME", nullable = false)
    private Date startTime;
    @Column(name = "DURATION")
    private long duration;
    @Column(name = "CRM_CONTACT_ID")
    private String crmContactId;
    @Column(name = "END_REASON")
    private String endReason;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALL_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTS_REC_CALLS"))
    private Call call;
    @OneToMany(mappedBy = "callPart", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<CallParticipant> participants = new ArrayList();

    @Override
    public CallState state() {
        return finishState;
    }
}

