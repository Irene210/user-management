package com.hisense.development.service;

import com.hisense.development.Loggable;
import com.hisense.development.dao.UserDao;
import com.hisense.development.entity.User;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean changePassword(String username, String newPassword) {
        try {
            if (exists(username)) {
                User user = find(username).get();
                user.setPassword(newPassword);
                passwordHelper.encryptPassword(user);
                return userDao.update(user);
            }
        } catch (Exception e) {
            logger().info("Changing password failure");
        }
        return false;
    }


    public Boolean exists(String username) {
        return find(username).map(user -> !user.equals(new User())).getOrElse(false);
    }
}
