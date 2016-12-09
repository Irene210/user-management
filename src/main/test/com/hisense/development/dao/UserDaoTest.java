package com.hisense.development.dao;

import com.hisense.development.entity.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserDaoTest extends TestCase {
    @Autowired
    UserDao userDao;
    private  User irene;

    @Before
    public void beforeTest() throws Exception{
         irene = new User("irene","123");
        userDao.create(irene);
    }
    @After
    public void afterTest() throws Exception{
        userDao.deleteAll();
    }

    @Test
    public void testCreateUser()throws Exception {
        int size = userDao.findAll().size();
        userDao.create(new User("demo","123"));
        assertEquals(size+1,userDao.findAll().size());
    }

    @Test
    public void testDelete()throws Exception {
        User demo = new User("demo", "123");
        userDao.create(demo);
        List<String> list = new LinkedList<String>();
        list.add(irene.getUsername());
        list.add(demo.getUsername());
        boolean b = userDao.delete(list);
        assertTrue(b);
    }

    @Test
    public void testFindByName() throws Exception{
        User user = userDao.find(irene.getUsername());
        assertEquals(irene.getUsername(),user.getUsername());
        assertEquals(irene.getPassword(),user.getPassword());
        assertTrue(user.getId()>=0);
    }

    @Test
    public void testFindAll() throws Exception{
        User demo = new User("demo", "123");
        userDao.create(demo);
        assertTrue(userDao.findAll().size()==2);
        assertTrue(userDao.findAll().toString().contains(demo.toString()));
        assertTrue(userDao.findAll().toString().contains(irene.toString()));
    }

    @Test
    public void testUpdateUser()throws Exception {
        User irene = userDao.find("irene");
        irene.setSex("woman");
        irene.setDisplayNum(1);
        userDao.update(irene);
        User user = userDao.find("irene");
        assertTrue(user.getSex().equals("woman"));
        assertTrue(user.getDisplayNum()==1);
    }

}
