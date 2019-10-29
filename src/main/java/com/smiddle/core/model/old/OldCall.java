package com.smiddle.core.model.old;

import com.smiddle.core.model.old.extra.OldCallParticipant;
import com.smiddle.license.core.holder.LicenseHolder;
import com.smiddle.rec.model.calls.CallDirection;
import com.smiddle.rec.model.calls.CallState;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "REC_CALLS")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OldCall {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "MS_SESSION_ID", nullable = false)
    private String msSessionId;
    @Column(name = "CRM_CALL_ID")
    private String crmCallId;
    @Column(name = "CCID")
    private String ccid;
    @Type(type = "timestamp")
    @Column(name = "REG_DATE", nullable = false)
    private Date dateReg;
    @Type(type = "timestamp")
    @Column(name = "START_DATE", nullable = false)
    private Date dateStart;
    @Type(type = "timestamp")
    @Column(name = "FINISH_DATE")
    private Date dateFinish;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "DIRECTION", nullable = false)
    private CallDirection direction;
    @Column(name = "DURATION", nullable = false)
    private long duration;
    @Column(name = "RTSP_LINK")
    private String rtspLink;
    @Column(name = "MP4_LINK")
    private String mp4Link;
    @Column(name = "IS_MS_REMOVED", nullable = false)
    private boolean msRemoved;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "LICENSED", nullable = false)
    private LicenseHolder.LicenseStatus licensed;
    @Column(name = "LICENSE_NUMBER", nullable = false)
    private volatile int licenseNumber;
    @Column(name = "TRACK_PATH")
    private String trackPath;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "FINISH_STATE", nullable = false)
    private volatile CallState finishState;
    @Column(name = "IS_MAPPED", nullable = false)
    private volatile boolean mapped;
    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER)
    private List<OldCallParticipant> participants = new ArrayList<>();
}
