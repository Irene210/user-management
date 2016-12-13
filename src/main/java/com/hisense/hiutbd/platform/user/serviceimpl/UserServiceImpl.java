package com.hisense.hiutbd.platform.user.serviceimpl;

import com.hisense.hiutbd.platform.Loggable;

import com.hisense.hiutbd.platform.base.domain.Role;
import com.hisense.hiutbd.platform.base.domain.User;
import com.hisense.hiutbd.platform.base.domain.UserRole;
import com.hisense.hiutbd.platform.base.persistence.UserMapper;
import com.hisense.hiutbd.platform.user.service.UserService;
import javaslang.collection.List;
import javaslang.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaslang.collection.Set;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@Service
public class UserServiceImpl extends AbstractBaseService<User> implements Loggable, UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    public Either<Exception, Boolean> create(User user) {
        if (user.getUsername() != null) {
            if (userMapper.find(user.getUsername()) == null) {
                passwordHelper.encryptPassword(user);
                return doAction(() -> userMapper.create(user));
            }
        }
        return Either.left(new Exception());
    }

    public Either<Exception, Boolean> changePassword(Long id, String newPassword) {
        return find(id).map(s -> {
            s.setPassword(newPassword);
            passwordHelper.encryptPassword(s);
            return update(s);
        }).getOrElse(Either.left(new Exception()));

    }

    public Boolean correlationRoles(Long userId, Set<Long> roleIds) {
        return roleIds.map(roleId-> userMapper.correlationRoles(new UserRole(userId,roleId))).forAll(s->s);

    }

    public Either<Exception, Boolean> uncorrelationRoles(Long userId) {
        return doAction(()-> userMapper.uncorrelationRoles(userId));

    }

    public Either<Exception, Set<Role>> findRoles(Long userId){
        return doAction(() -> List.ofAll(userMapper.findRoles(userId)).toSet());
    }
}
