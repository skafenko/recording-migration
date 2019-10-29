package com.smiddle.core.dao;

import com.smiddle.core.model.Group;
import org.springframework.transaction.annotation.Transactional;

public interface GroupDAO {

    void saveGroup(Group group);

    @Transactional(readOnly = true)
    long getCount();
}
