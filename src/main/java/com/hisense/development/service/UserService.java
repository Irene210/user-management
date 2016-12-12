package com.hisense.development.service;

import com.hisense.development.entity.User;
import javaslang.control.Either;
import javaslang.control.Option;

import javaslang.collection.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public interface UserService extends BaseService<User>{

    public boolean changePassword(String username, String newPassword);
//
//    public void correlationRoles(Long userId, Long... roleIds);
//
//    public void uncorrelationRoles(Long userId, Long... roleIds);
//
//
//    public Set<String> findRoles(String username);

}
