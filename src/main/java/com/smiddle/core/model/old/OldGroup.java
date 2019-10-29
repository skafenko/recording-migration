package com.smiddle.core.model.old;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_GROUPS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OldGroup {
    public static final long serialVersionUID = -1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = "FK_ADM_GROUPS_ADM_GROUPS_ID"))
    private OldGroup parent;
    @Column(name = "COMPANY_ID", nullable = false)
    private long CID;
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OldGroup> children;
}
