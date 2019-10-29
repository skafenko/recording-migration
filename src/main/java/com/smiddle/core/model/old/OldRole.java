package com.smiddle.core.model.old;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "ADM_ROLES")
@Data
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
}
