package com.smiddle.core.model.old.extra;

import com.smiddle.core.model.old.OldCall;
import com.smiddle.core.model.old.OldUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REC_CALL_PARTICIPANTS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"agentId", "phone", "xRefCi", "date", "duration", "id"})
@Data
public class OldCallParticipant {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private long id;
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
    @ManyToOne
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTICIPANTS_ADM_USERS"))
    private OldUser user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALL_ID", foreignKey = @ForeignKey(name = "FK_CALL_PARTICIPANTS_REC_CALLS"))
    private OldCall call;
}
