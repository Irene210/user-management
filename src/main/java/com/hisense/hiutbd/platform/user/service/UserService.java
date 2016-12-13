package com.hisense.hiutbd.platform.user.service;

import com.hisense.hiutbd.platform.base.domain.Role;
import com.hisense.hiutbd.platform.base.domain.User;
import javaslang.collection.Set;
import javaslang.control.Either;


/**
 * Created by Administrator on 2016/12/7 0007.
 */
public interface UserService extends BaseService<User> {

    public Either<Exception, Boolean> changePassword(Long id, String newPassword);

    public Boolean correlationRoles(String userId, Set<String> roleIds);

    public Either<Exception, Boolean> uncorrelationRoles(String userId);


    public Either<Exception, Set<Role>> findRoles(String userId);

}
