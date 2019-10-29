package com.smiddle.core.model;

import com.smiddle.core.model.extra.CallParticipant;
import com.smiddle.core.model.extra.CallParts;
import com.smiddle.core.model.extra.CallStatistic;
import com.smiddle.core.util.RidGenerator;
import com.smiddle.license.core.holder.LicenseHolder;
import com.smiddle.rec.model.calls.CallContentType;
import com.smiddle.rec.model.calls.CallDirection;
import com.smiddle.rec.model.calls.CallParticipantType;
import com.smiddle.rec.model.calls.CallState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "REC_CALLS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Call {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    protected volatile Long id;
    @Column(name = "RID")
    @GeneratorType(type = RidGenerator.class)
    protected String rid;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECTION", nullable = false)
    private CallDirection direction;
    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE", nullable = false)
    private CallContentType contentType;
    @Column(name = "DURATION", nullable = false)
    private long duration;
    @Column(name = "RTSP_LINK")
    private String rtspLink;
    @Column(name = "MP4_LINK")
    private String mp4Link;
    @Column(name = "IS_MS_REMOVED", nullable = false)
    private boolean msRemoved;
    @Column(name = "IS_RECOGNIZED")
    private boolean recognized;
    @Column(name = "IS_COMMENTED")
    private boolean commented;
    @Enumerated(EnumType.STRING)
    @Column(name = "LICENSED", nullable = false)
    private LicenseHolder.LicenseStatus licensed;
    @Column(name = "LICENSE_NUMBER", nullable = false)
    private volatile int licenseNumber;
    @Column(name = "TRACK_PATH")
    private String trackPath;
    @Column(name = "SUCCESS")
    private String success;
    @Enumerated(EnumType.STRING)
    @Column(name = "FINISH_STATE", nullable = false)
    private volatile CallState finishState;
    @Column(name = "IS_MAPPED", nullable = false)
    private volatile boolean mapped;
    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Set<CallParticipant> participants = new HashSet<>();
    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<CallParts> parts = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "CALL_PARTICIPANT_TYPE")
    private CallParticipantType callParticipantType;
    @JoinColumn(name = "STATISTIC_ID")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private CallStatistic callStatistic;
}