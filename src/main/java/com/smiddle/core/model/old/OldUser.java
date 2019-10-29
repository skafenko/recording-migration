package com.smiddle.core.model.old;

import com.smiddle.core.model.old.extra.OldDomain;
import com.smiddle.core.model.old.extra.OldUserGroupRoles;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_USERS")
@Data
@NoArgsConstructor
public class OldUser {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "AGENT_ID")
    private String agentId;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "LOGIN_AD")
    private String loginAD;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "LAST_NAME", nullable = false)
    private String lname;
    @Column(name = "FIRST_NAME", nullable = false)
    private String fname;
    @Column(name = "PATRONYMIC_NAME")
    private String pname;
    @Column(name = "E_MAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "DATE_CREATE", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ADM_USERS_ADM_GROUPS", uniqueConstraints = @UniqueConstraint(name = "UK_ADM_USERS_ID_ADM_GROUPS_ID", columnNames = {"ADM_USERS_ID", "ADM_GROUPS_ID"}),
            joinColumns = {@JoinColumn(name = "ADM_USERS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USERS_GROUPS_ADM_USERS_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "ADM_GROUPS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USERS_GROUPS_ADM_GROUPS_ID"))})
    private List<OldGroup> groups = new ArrayList<>();
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ADM_USERS_ADM_ROLES", uniqueConstraints = @UniqueConstraint(name = "UK_ADM_USERS_ID_ADM_ROLES_ID", columnNames = {"ADM_USERS_ID", "ADM_ROLES_ID"}),
            joinColumns = {@JoinColumn(name = "ADM_USERS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USERS_ROLES_ADM_USERS_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "ADM_ROLES_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USERS_ROLES_ADM_ROLES_ID"))})
    private List<OldRole> roles = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOMAIN_ID", foreignKey = @ForeignKey(name = "FK_ADM_USERS_ADM_DOMAINS_ID"))
    private OldDomain domain;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
    private List<OldUserGroupRoles> userGroupRoles = new ArrayList<>();
    @Column(name = "LOCALE")
    private String locale;
    @Column(name = "SC_MODE", nullable = false)
    private int scMode;
    @Column(name = "UNMAPPED_CALLS", nullable = false)
    private boolean unmappedCalls;
}
