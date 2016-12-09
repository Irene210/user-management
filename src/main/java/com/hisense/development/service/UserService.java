package com.hisense.development.service;

import com.hisense.development.entity.User;
import javaslang.control.Either;
import javaslang.control.Option;

import javaslang.collection.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public interface UserService {
    public boolean create(User user);

    public boolean delete(List<String> usernames);

    public boolean update(User user);

    public boolean deleteAll();

//    public void changePassword(Long userId, String newPassword);
//
//    public void correlationRoles(Long userId, Long... roleIds);
//
//    public void uncorrelationRoles(Long userId, Long... roleIds);
//
    public Option<User> findByUsername(String username);
//
//    public Set<String> findRoles(String username);

}
