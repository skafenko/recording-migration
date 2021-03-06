package com.smiddle.core.model.extra;

import com.smiddle.common.model.BaseEntity;
import com.smiddle.core.model.Group;
import com.smiddle.core.model.Role;
import com.smiddle.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ADM_USER_GROUP_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroupRoles extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_GROUP", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_GROUP"), nullable = false)
    private Group group;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_ROLE", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_ROLE"), nullable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADM_UGR_USER", foreignKey = @ForeignKey(name = "FK_ADM_UGR_ADM_USER"), nullable = false)
    private User user;
}
