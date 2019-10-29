package com.smiddle.core.dao;

import com.smiddle.core.model.Group;

public interface GroupDAO {

    void saveGroup(Group group);

    long getCount();
}
