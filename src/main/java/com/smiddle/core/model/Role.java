package com.smiddle.core.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_ROLES")
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Role {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    protected volatile Long id;
    @Column(name = "RID")
    protected volatile String rid;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "IS_SYSTEM", nullable = false)
    private boolean system;
    @ColumnDefault("86400")
    @Column(name = "SESSION_LIVE_TIME_SEC", nullable = false)
    private int sessionLiveTimeSec;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_ADM_ROLES_ADM_GROUPS_ID"))
    private Group group;
}
