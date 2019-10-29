package com.smiddle.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_GROUPS",
        uniqueConstraints = @UniqueConstraint(name = "UK_ADM_GROUPS_RID", columnNames = {"RID"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    public static final long serialVersionUID = -1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected volatile Long id;
    @Column(name = "RID")
    protected volatile String rid;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = "FK_ADM_GROUPS_ADM_GROUPS_ID"))
    private Group parent;
    @Column(name = "COMPANY_ID", nullable = false)
    private long CID;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> children = new ArrayList<>();
}
