package com.smiddle.core.model.old.extra;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.smiddle.common.model.View;
import com.smiddle.core.model.old.OldUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "ADM_DOMAINS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ADM_DOMAINS_LDAP_SERVER_IP", columnNames = {"LDAP_SERVER_IP"}),
                @UniqueConstraint(name = "UK_ADM_DOMAINS_DOMAIN_NAME", columnNames = {"DOMAIN_NAME"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OldDomain {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private long id;
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
    private List<OldUser> users = new ArrayList<>();
}
