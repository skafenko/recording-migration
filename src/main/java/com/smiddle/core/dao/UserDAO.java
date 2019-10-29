package com.smiddle.core.dao;

import com.smiddle.core.model.User;

public interface UserDAO {

    void saveUser(User user);

    long getCount();
}
