//package com.smiddle.core.model.extra;
//
//import com.smiddle.common.model.RidGenerator;
//import com.smiddle.core.model.Group;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenerationTime;
//import org.hibernate.annotations.GeneratorType;
//
//import javax.persistence.*;
//
//@Entity
//@Table(
//        name = "REC_TAGS",
//        uniqueConstraints = {@UniqueConstraint(
//                name = "UK_REC_TAGS_TAG",
//                columnNames = {"TAG", "GROUP_ID"}
//        ), @UniqueConstraint(
//                name = "UK_REC_TAGS_RID",
//                columnNames = {"RID"}
//        )}
//)
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Tag {
//    public static final long serialVersionUID = -1L;
//    @Id
//    @Column(name = "ID")
//    protected volatile Long id;
//    @GeneratorType(type = RidGenerator.class, when = GenerationTime.INSERT)
//    @Column(name = "RID")
//    protected volatile String rid;
//    @Column(name = "TAG", nullable = false)
//    private String name;
//    @Column(name = "TAG_DESCRIPTION")
//    private String description;
//    @ManyToOne
//    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_REC_TAGS_ADM_GROUPS_ID"))
//    private Group group;
//}
