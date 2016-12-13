package com.hisense.hiutbd.platform.base.persistence;


import com.hisense.hiutbd.platform.base.domain.Role;
import com.hisense.hiutbd.platform.base.domain.User;
import com.hisense.hiutbd.platform.base.domain.UserRole;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    public Boolean correlationRoles(UserRole userRole);

    public Boolean uncorrelationRoles(Long id);

    public Set<Role> findRoles(Long id);
}