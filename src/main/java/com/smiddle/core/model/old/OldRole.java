package com.smiddle.core.model.old;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_ROLES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ADM_ROLES_NAME", columnNames = {"NAME", "GROUP_ID"})
        })
@Getter
@Setter
@NoArgsConstructor
public class OldRole {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "IS_SYSTEM", nullable = false)
    private boolean system;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_ADM_ROLES_ADM_GROUPS_ID"))
    private OldGroup group;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "ADM_TASKS_ADM_ROLES", uniqueConstraints = @UniqueConstraint(name = "UK_ADM_TASK_ID_ADM_ROLE_ID", columnNames = {"ADM_TASKS_ID", "ADM_ROLES_ID"}),
//            inverseJoinColumns = {@JoinColumn(name = "ADM_TASKS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASKS_ROLES_ADM_TASKS_ID"))},
//            joinColumns = {@JoinColumn(name = "ADM_ROLES_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASKS_ROLES_ADM_ROLES_ID"))})
//    @Fetch(FetchMode.SUBSELECT)
//    private List<OldTaskUrl> tasks = new ArrayList<>();

    public OldRole(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("name='").append(name).append('\'');
        sb.append(", system=").append(system);
        sb.append(", group=").append(group);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public static final class System {
        public static final String ROOT = "ROOT";
        public static final String ADMINISTRATOR = "ADMINISTRATOR";
        public static final String SUPERVISOR = "SUPERVISOR";
        public static final String USER = "USER";
    }

}
