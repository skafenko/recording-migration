package com.smiddle.core.dao.old;

import com.smiddle.core.model.old.OldUser;

import java.util.List;

public interface OldUserDAO {

    List<OldUser> getAllOldUsers(int limit, int offset);

    long getCount();
}
