package com.hisense.development.dao;


import com.hisense.development.entity.User;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao {

    public int createUser(User user);

    public List<User> findAll();
    public int deleteAll();


}
