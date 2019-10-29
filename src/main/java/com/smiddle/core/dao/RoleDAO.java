package com.smiddle.core.dao;

import com.smiddle.core.model.Role;

public interface RoleDAO {

    void saveAllRoles(Role role);

    long getCount();
}
