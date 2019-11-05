package com.smiddle.core.service.impl.migration;

import com.smiddle.core.dao.RoleDAO;
import com.smiddle.core.dao.old.OldRoleDAO;
import com.smiddle.core.model.Group;
import com.smiddle.core.model.Role;
import com.smiddle.core.model.old.OldRole;
import com.smiddle.core.service.MigrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class OldRoleMigrationServiceImpl implements MigrationService {
    private static final int DEFAULT_SESSION_LIVE_TIME_SEC = 84600;
    private final OldRoleDAO oldRoleDAO;
    private final RoleDAO roleDAO;

    @Override
    public void migrate() {
        log.info("STARTED roles migration");
        List<OldRole> oldRoles = oldRoleDAO.getAllOldRoles();
        long rolesCount = roleDAO.getCount();
        if (rolesCount != 0) {
            log.info("FINISHED roles migration because not need");
            return;
        }
        List<Role> newRoles = migrateOldRolesToRoles(oldRoles);
        newRoles.forEach(roleDAO::saveAllRoles);
        log.info("FINISHED roles migration");
    }

    private List<Role> migrateOldRolesToRoles(List<OldRole> oldRoles) {
        return oldRoles.stream()
                .map(this::migrateOldRoleToRole)
                .collect(Collectors.toList());
    }

    private Role migrateOldRoleToRole(OldRole oldRole) {
        Role role = new Role();
        role.setRid(UUID.randomUUID().toString());
        role.setId(oldRole.getId());
        role.setName(oldRole.getName());
        role.setSystem(oldRole.isSystem());
        role.setSessionLiveTimeSec(DEFAULT_SESSION_LIVE_TIME_SEC);
        if (Objects.nonNull(oldRole.getGroup())) {
            role.setGroup(Group.builder().id(oldRole.getGroup().getId()).build());
        }
        return role;
    }
}
