//package com.smiddle.core.model.extra;
//
//import com.fasterxml.jackson.annotation.*;
//import com.smiddle.common.model.Access;
//import com.smiddle.common.model.HttpMethod;
//import com.smiddle.common.model.KeyEntity;
//import com.smiddle.common.model.View;
//import com.smiddle.core.model.Role;
//import com.smiddle.core.model.old.extra.OldTaskUrl;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Entity
//@Table(name = "ADM_TASKS",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "UK_ADM_TASKS_URL", columnNames = {"URL", "REQUEST_METHOD"}),
//                @UniqueConstraint(name = "UK_ADM_TASKS_CODE", columnNames = {"CODE"}),
//                @UniqueConstraint(name = "UK_ADM_TASKS_RID", columnNames = {"RID"})},
//        indexes =
//        @Index(name = "IDX_ADM_TASKS_ACCESS_TYPE", columnList = "ACCESS_TYPE"))
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "authorizationTypes", "access", "name", "str", "roleList"})
//@Data
//@NoArgsConstructor
//public class TaskUrl extends KeyEntity {
//    // TODO: 2/15/18 for MS SQL not allowed multiple NULL (UK_ADM_TASKS_URL) - need resolve
//    public static final long serialVersionUID = -1L;
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
//    @Enumerated(EnumType.STRING)
//    @Column(name = "REQUEST_METHOD", nullable = true)
//    private HttpMethod method;
//    @Fetch(FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tasks")
//    private List<Role> roleList = new ArrayList<>();
//    @Enumerated(EnumType.STRING)
//    @Column(name = "ACCESS_TYPE", nullable = false, length = 20, columnDefinition = "varchar(20) default 'PRINCIPAL'")
//    private Access access;
//    @Enumerated(EnumType.STRING)
//    @Fetch(FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "ADM_TASKS_ADM_AUTH_TYPE", uniqueConstraints = @UniqueConstraint(name = "UK_ADM_TASK_ID_AUTH_TYPE_ID", columnNames = {"ADM_TASKS_ID", "ADM_AUTH_TYPE_ID"}),
//            joinColumns = {@JoinColumn(name = "ADM_TASKS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASK_AUTH_ADM_TASK_ID"))},
//            inverseJoinColumns = {@JoinColumn(name = "ADM_AUTH_TYPE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TASK_AUTH_ADM_AUTH_ID"))})
//    private List<AuthorizationType> authorizationTypes = new ArrayList<>();
//
//    public TaskUrl (OldTaskUrl oldTaskUrl){
//        oldTaskUrl.getAccess();
//        oldTaskUrl.getCode();
//        oldTaskUrl.getModule();
//        oldTaskUrl.getName();
//        oldTaskUrl.getRoleList();
//
//    }
//
//    public TaskUrl(String url, String name, String code, String module) {
//        this.url = url;
//        this.name = name;
//        this.code = code;
//        this.module = module;
//        this.access = Access.PRINCIPAL;
//    }
//
//    public TaskUrl(String url, String name, String code, String module, HttpMethod method, Access access) {
//        this(url, name, code, module);
//        this.method = method;
//        this.access = access;
//    }
//
//    //Getters and setters
//    @Override
//    @JsonGetter("taskUrlId")
//    @JsonView(View.Permission.class)
//    public String getRid() {
//        return super.getRid();
//    }
//
//    @Override
//    @JsonSetter("taskUrlId")
//    public void setRid(String rid) {
//        super.setRid(rid);
//    }
//
//        private boolean compareLists(List<?> list1, List<?> list2) {
//        return list1 == null && list2 == null || list1 != null && list2 != null && new ArrayList<>(list1).equals(new ArrayList<>(list2));
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), module, code, name, url, method, roleList, access, authorizationTypes);
//    }
//
//    //Methods
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("TaskURL{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", url='").append(url).append('\'');
//        sb.append(", roleList=").append(roleList);
//        sb.append(", module=").append(module);
//        sb.append(", code=").append(code);
//        sb.append(", authorizationTypes=").append(str.apply(authorizationTypes));
//        sb.append('}');
//        return sb.toString();
//    }
//
//    @Transient
//    private Function<Collection<AuthorizationType>, String> str = (list) -> list != null ? list.stream()
//            .map(AuthorizationType::getType)
//            .map(Enum::name)
//            .collect(Collectors.joining("", "[", "]"))
//            : null;
//}
