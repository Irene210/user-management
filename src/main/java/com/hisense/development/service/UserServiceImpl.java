package com.hisense.development.service;

import com.hisense.development.Loggable;
import com.hisense.development.dao.UserDao;
import com.hisense.development.entity.Role;
import com.hisense.development.entity.User;
import com.hisense.development.entity.UserRole;
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
    UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;

    public Either<Exception, Boolean> create(User user) {
        if (user.getName() != null) {
            if (userDao.find(user.getName()) == null) {
                passwordHelper.encryptPassword(user);
                return doAction(() -> userDao.create(user));
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
        return roleIds.map(roleId-> userDao.correlationRoles(new UserRole(userId,roleId))).forAll(s->s);

    }

    public Either<Exception, Boolean> uncorrelationRoles(Long userId) {
        return doAction(()->userDao.uncorrelationRoles(userId));

    }

    public Either<Exception, Set<Role>> findRoles(Long userId){
        return doAction(() -> List.ofAll(userDao.findRoles(userId)).toSet());
    }
}
