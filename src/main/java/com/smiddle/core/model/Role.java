package com.smiddle.core.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_ROLES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ADM_ROLES_NAME", columnNames = {"NAME", "GROUP_ID"}),
                @UniqueConstraint(name = "UK_ADM_ROLES_RID", columnNames = {"RID"})})
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
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "ADM_TASKS_ADM_ROLES", uniqueConstraints = @UniqueConstraint(name = "UK_ADM_TASK_ID_ADM_ROLE_ID", columnNames = {"ADM_TASKS_ID", "ADM_ROLES_ID"}),
//            inverseJoinColumns = {@JoinColumn(name = "ADM_TASKS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASKS_ROLES_ADM_TASKS_ID"))},
//            joinColumns = {@JoinColumn(name = "ADM_ROLES_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASKS_ROLES_ADM_ROLES_ID"))})
//    @Fetch(FetchMode.SUBSELECT)
//    private List<TaskUrl> tasks = new ArrayList<>();
}
