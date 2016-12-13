package com.hisense.development.dao;


import com.hisense.development.entity.Role;
import com.hisense.development.entity.User;
import com.hisense.development.entity.UserRole;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao extends BaseDao<User> {

    public Boolean correlationRoles(UserRole userRole);

    public Boolean uncorrelationRoles(Long id);

    public Set<Role> findRoles(Long id);
}