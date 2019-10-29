package com.smiddle.core.model.extra;

import com.smiddle.common.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADM_AUTHORIZATION_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString(callSuper = true)
public class AuthorizationType extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_TYPE", nullable = false, unique = true)
    private com.smiddle.common.model.AuthorizationType.Type type;

    public enum Type {
        BASIC, SMIDDLE_TOKEN
    }
}
