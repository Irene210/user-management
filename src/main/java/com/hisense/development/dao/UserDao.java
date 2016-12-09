package com.hisense.development.dao;


import com.hisense.development.entity.User;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao {

    public boolean createUser(User user) throws Exception;

    public List<User> findAll() throws Exception ;

    public User findByUsername(String name) throws Exception ;

    public boolean deleteAll() throws Exception ;

    public boolean deleteUser(List<String> usernames) throws Exception ;

    public boolean updateUser(User user) throws Exception ;


}
