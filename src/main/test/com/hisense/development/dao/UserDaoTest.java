package com.hisense.development.dao;

import com.hisense.development.entity.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest extends TestCase{
    @Autowired
    UserDao userDao;

    @Before
    public void beforeTest(){
        userDao.deleteAll();
    }

    @Test
    public void testCreateUser() {
        int size = userDao.findAll().size();
        User user = new User("irene","123");
        user.setSex("dsd");
        int user1 = userDao.createUser(user);
        assertEquals(size+1,userDao.findAll().size());
    }

//    @Test
//    public void delete() {
//        int size = userDao.delete();
//        User user = new User();
//        int user1 = userDao.createUser(user);
//        assertEquals(size+1,userDao.findAll().size());
//    }

}
