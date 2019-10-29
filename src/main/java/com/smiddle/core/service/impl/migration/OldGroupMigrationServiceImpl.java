package com.smiddle.core.service.impl.migration;

import com.smiddle.core.dao.GroupDAO;
import com.smiddle.core.dao.old.OldGroupDAO;
import com.smiddle.core.model.Group;
import com.smiddle.core.model.old.OldGroup;
import com.smiddle.core.service.MigrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Order(1)
@Slf4j
public class OldGroupMigrationServiceImpl implements MigrationService {
    private final OldGroupDAO oldGroupDAO;
    private final GroupDAO groupDAO;

    @Override
    public void migrate() {
        log.info("STARTED groups migration");
        List<OldGroup> oldGroups = oldGroupDAO.getAllOldGroupsWithParentNull();
        long groupCount = groupDAO.getCount();
        if (groupCount > 1) {
            log.info("FINISHED groups migration because not need");
            return;
        }
        oldGroups.stream()
                .map(oldGroup -> migrateOldGroupToGroup(oldGroup, null))
                .forEach(groupDAO::saveGroup);
        log.info("FINISHED groups migration");
    }

    private List<Group> migrateOldGroupsToGroups(List<OldGroup> oldGroups, Group parent) {
        return oldGroups.stream()
                .map(oldGroup -> migrateOldGroupToGroup(oldGroup, parent))
                .collect(Collectors.toList());
    }

    private Group migrateOldGroupToGroup(OldGroup oldGroup, Group parent) {
        if (oldGroup == null) {
            return null;
        }
        Group group = new Group();
        group.setRid(UUID.randomUUID().toString());
        group.setId(oldGroup.getId());
        group.setName(oldGroup.getName());
        group.setCID(oldGroup.getCID());
        group.setParent(parent);
        group.setChildren(migrateOldGroupsToGroups(oldGroup.getChildren(), group));
        return group;
    }
}
