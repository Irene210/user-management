package com.hisense.development.service;

import com.hisense.development.Loggable;
import com.hisense.development.dao.UserDao;
import com.hisense.development.entity.User;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaslang.collection.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@Service
public class UserServiceImpl implements Loggable, UserService {
    @Autowired
    UserDao userDao;
//    @Autowired
//    private PasswordHelper passwordHelper;

    public boolean create(User user) {
        if (user.getUsername() != null) {
            try {
                if (userDao.find(user.getUsername()) == null) {
//                    passwordHelper.encryptPassword(user);
                    return userDao.create(user);
                }
            } catch (Exception e) {
                logger().info("Creating user failure");
            }
        }
        return false;
    }

    public boolean deleteAll() {
        try {
            return userDao.deleteAll();
        } catch (Exception e) {
            logger().info("Deleting all users failure");
        }
        return false;
    }

    public boolean delete(List<String> usernames) {
        try {
            if (usernames.forAll(username -> exists(username))) {
                return userDao.delete(usernames.toJavaList());
            }
        } catch (Exception e) {
            logger().info("Deleting  users failure");
        }
        return false;
    }

    public boolean update(User user) {
        try {
            if (exists(user.getUsername())) {
                return userDao.update(user);
            }
        } catch (Exception e) {
            logger().info("Updating  users failure");
        }
        return false;
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        try {
            if (exists(username)) {
                User user = findByUsername(username).get();
                user.setPassword(newPassword);
//                passwordHelper.encryptPassword(user);
                return userDao.update(user);
            }
        } catch (Exception e) {
            logger().info("Changing password failure");
        }
        return false;
    }

    public Option<User> findByUsername(String username) {
        User user = new User();
        try {
            user = userDao.find(username);
        } catch (Exception e) {
            logger().info("Finding user failure");
        }
        return Option.of(user);
    }

    public Boolean exists(String username) {
        return findByUsername(username).map(user -> !user.equals(new User())).getOrElse(false);
    }
}
