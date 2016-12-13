package com.hisense.development.user.service;

import com.hisense.development.base.domain.Role;
import com.hisense.development.base.domain.User;
import javaslang.collection.Set;
import javaslang.control.Either;


/**
 * Created by Administrator on 2016/12/7 0007.
 */
public interface UserService extends BaseService<User> {

    public Either<Exception, Boolean> changePassword(Long id, String newPassword);

    public Boolean correlationRoles(Long userId, Set<Long> roleIds);

    public Either<Exception, Boolean> uncorrelationRoles(Long userId);


    public Either<Exception, Set<Role>> findRoles(Long userId);

}
