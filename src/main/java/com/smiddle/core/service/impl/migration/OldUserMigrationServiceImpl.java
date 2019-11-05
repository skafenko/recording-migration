package com.smiddle.core.service.impl.migration;

import com.smiddle.core.dao.UserDAO;
import com.smiddle.core.dao.old.OldUserDAO;
import com.smiddle.core.model.Group;
import com.smiddle.core.model.Role;
import com.smiddle.core.model.User;
import com.smiddle.core.model.extra.Domain;
import com.smiddle.core.model.extra.UserGroupRoles;
import com.smiddle.core.model.old.OldUser;
import com.smiddle.core.model.old.extra.OldDomain;
import com.smiddle.core.service.MigrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Order(3)
@Slf4j
public class OldUserMigrationServiceImpl implements MigrationService {
    private static final int DEFAULT_LIMIT_USERS = 100;
    private final OldUserDAO oldUserDAO;
    private final UserDAO userDAO;

    @Override
    public void migrate() {
        log.info("STARTED users migration");
        int offset = 0;
        long count = oldUserDAO.getCount();
        long usersCount = userDAO.getCount();
        if (usersCount > 1) {
            log.info("FINISHED users migration because not need");
            return;
        }
        while (count > offset) {
            oldUserDAO.getAllOldUsers(DEFAULT_LIMIT_USERS, offset).stream()
                    .map(this::migrateOldUserToUser)
                    .forEach(userDAO::saveUser);
            offset += DEFAULT_LIMIT_USERS;
        }
        log.info("FINISHED users migration");
    }

    private User migrateOldUserToUser(OldUser oldUser) {
        User user = new User();
        user.setId(oldUser.getId());
        user.setRid(UUID.randomUUID().toString());
        user.setAgentId(oldUser.getAgentId());
        user.setDateCreate(oldUser.getDateCreate());
        user.setDeleted(oldUser.isDeleted());
        user.setEnabled(oldUser.isEnabled());
        user.setFname(oldUser.getFname());
        user.setLname(oldUser.getLname());
        user.setEmail(oldUser.getEmail());
        user.setFax(oldUser.getFax());
        user.setPhone(oldUser.getPhone());
        user.setPname(oldUser.getPname());
        user.setLogin(oldUser.getLogin());
        user.setPassword(oldUser.getPassword());
        user.setLoginAD(oldUser.getLoginAD());
        user.setLocale(oldUser.getLocale());
        user.setScMode(oldUser.getScMode());
        user.setUnmappedCalls(oldUser.isUnmappedCalls());
        user.setDomain(migrateOldDomainToDomain(oldUser.getDomain()));
        if (!oldUser.getGroups().isEmpty()) {
            List<Group> groups = oldUser.getGroups().stream()
                    .map(oldGroup -> Group.builder().id(oldGroup.getId()).build())
                    .collect(Collectors.toList());
            user.setGroups(groups);
        }
        if (!oldUser.getRoles().isEmpty()) {
            List<Role> roles = oldUser.getRoles().stream()
                    .map(oldRole -> Role.builder().id(oldRole.getId()).build())
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }
        setUpUserGroupRoles(oldUser, user);
        return user;
    }

    private void setUpUserGroupRoles(OldUser oldUser, User user) {
        Set<UserGroupRoles> userGroupRoles = new HashSet<>();
        oldUser.getGroups().stream()
                .flatMap(oldGroup -> oldUser.getRoles().stream().map(oldRole -> UserGroupRoles.builder()
                        .group(Group.builder().id(oldGroup.getId()).build())
                        .role(Role.builder().id(oldRole.getId()).build())
                        .user(User.builder().id(oldUser.getId()).build())
                        .build()))
                .forEach(userGroupRoles::add);
        if (!oldUser.getUserGroupRoles().isEmpty()) {
            oldUser.getUserGroupRoles().stream()
                    .map(oldUserGroupRoles -> UserGroupRoles.builder()
                            .group(Group.builder().id(oldUserGroupRoles.getGroup().getId()).build())
                            .role(Role.builder().id(oldUserGroupRoles.getRole().getId()).build())
                            .user(User.builder().id(oldUserGroupRoles.getUser().getId()).build())
                            .build())
                    .forEach(userGroupRoles::add);
        }
        user.setUserGroupRoles(new ArrayList<>(userGroupRoles));
    }

    private Domain migrateOldDomainToDomain(OldDomain oldDomain) {
        if (oldDomain == null) {
            return null;
        }
        Domain domain = new Domain();
        domain.setId(oldDomain.getId());
        domain.setRid(UUID.randomUUID().toString());
        domain.setDomainName(oldDomain.getDomainName());
        domain.setDescription(oldDomain.getDescription());
        domain.setServiceUser(oldDomain.getServiceUser());
        domain.setLdapServerIp(oldDomain.getLdapServerIp());
        domain.setPerPage(oldDomain.getPerPage());
        domain.setServiceUser(oldDomain.getServiceUser());
        domain.setServiceUserPassword(oldDomain.getServiceUserPassword());
        return domain;
    }
}
