package com.smiddle.core.model.old.extra;

import com.smiddle.core.model.old.OldGroup;
import com.smiddle.core.model.old.OldRole;
import com.smiddle.core.model.old.OldUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ADM_USER_GROUP_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OldUserGroupRoles {
    @Id
    @Column(name = "ID")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_GROUP", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_GROUP"), nullable = false)
    private OldGroup group;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_ROLE", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_ROLE"), nullable = false)
    private OldRole role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_USER", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_USER"), nullable = false)
    private OldUser user;
}
