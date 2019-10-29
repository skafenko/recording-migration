//package com.smiddle.core.model.old.extra;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonView;
//import com.smiddle.common.model.Access;
//import com.smiddle.common.model.HttpMethod;
//import com.smiddle.common.model.View;
//import com.smiddle.core.model.old.OldRole;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "ADM_TASKS",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "UK_ADM_TASKS_URL", columnNames = {"URL", "REQUEST_METHOD"}),
//                @UniqueConstraint(name = "UK_ADM_TASKS_CODE", columnNames = {"CODE"})},
//        indexes =
//        @Index(name = "IDX_ADM_TASKS_ACCESS_TYPE", columnList = "ACCESS_TYPE"))
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "authorizationTypes", "access", "name", "str", "roleList"})
//@Data
//@NoArgsConstructor
//public class OldTaskUrl {
//    // TODO: 2/15/18 for MS SQL not allowed multiple NULL (UK_ADM_TASKS_URL) - need resolve
//    public static final long serialVersionUID = -1L;
//    @Id
//    @Column(name = "ID")
//    private long id;
//    @Column(name = "MODULE", nullable = false)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String module;
//    @Column(name = "CODE", nullable = false)
//    @JsonView(View.Permission.class)
//    private String code;
//    @Column(name = "TASK_NAME")
//    private String name;
//    @Column(name = "URL", nullable = true)
//    private String url;
//    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
//    private List<OldRole> roleList = new ArrayList<>();
//    @Enumerated(EnumType.STRING)
//    @Column(name = "ACCESS_TYPE", nullable = false, length = 20, columnDefinition = "varchar(20) default 'PRINCIPAL'")
//    private Access access;
//
//    public OldTaskUrl(String url, String name, String code, String module) {
//        this.url = url;
//        this.name = name;
//        this.code = code;
//        this.module = module;
//        this.access = Access.PRINCIPAL;
//    }
//
//    public OldTaskUrl(String url, String name, String code, String module, Access access) {
//        this(url, name, code, module);
//        this.access = access;
//    }
//}
