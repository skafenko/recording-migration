package com.smiddle.core.model.extra;

import com.fasterxml.jackson.annotation.JsonView;
import com.smiddle.common.model.View;
import com.smiddle.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ADM_DOMAINS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ADM_DOMAINS_LDAP_SERVER_IP", columnNames = {"LDAP_SERVER_IP"}),
                @UniqueConstraint(name = "UK_ADM_DOMAINS_DOMAIN_NAME", columnNames = {"DOMAIN_NAME"}),
                @UniqueConstraint(name = "UK_ADM_DOMAINS_RID", columnNames = {"RID"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domain {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    protected volatile Long id;
    @Column(name = "RID")
    protected volatile String rid;
    @Column(name = "DOMAIN_NAME")
    @JsonView(View.Detailed.class)
    private String domainName;
    @Column(name = "LDAP_SERVER_IP")
    private String ldapServerIp;
    @Column(name = "SERVICE_USER")
    private String serviceUser;
    @Column(name = "SERVICE_USER_PASSWORD")
    private String serviceUserPassword;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PER_PAGE_AD")
    private Integer perPage;
    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
