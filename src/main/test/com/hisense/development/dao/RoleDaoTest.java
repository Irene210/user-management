package com.hisense.development.dao;

import com.hisense.development.entity.Role;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class RoleDaoTest extends TestCase {

    @Autowired
    RoleDao roleDao;
    private Role role;

    @Before
    public void beforeTest() throws Exception {
        role= new Role("role", true);
        roleDao.create(role);
    }

    @After
    public void afterTest() throws Exception {
        roleDao.deleteAll();
    }

    @Test
    public void testCreateUser() throws Exception {
        int size = roleDao.findAll().size();
        roleDao.create(new Role("role1", true));
        assertEquals(size + 1, roleDao.findAll().size());
    }

    @Test
    public void testDelete() throws Exception {
        Role demo = new Role("demo", true);
        roleDao.create(demo);
        List<String> list = new LinkedList<String>();
        list.add(role.getName());
        list.add(demo.getName());
        boolean b = roleDao.delete(list);
        assertTrue(b);
    }

    @Test
    public void testFindByName() throws Exception {
        Role role = roleDao.find(this.role.getName());
        assertEquals(this.role.getName(), role.getName());
        assertEquals(this.role.getAvailable(), role.getAvailable());
        assertTrue(role.getId() >= 0);
    }

    @Test
    public void testFindAll() throws Exception {
        Role demo = new Role("demo", true);
        roleDao.create(demo);
        assertTrue(roleDao.findAll().size() == 2);
        assertTrue(roleDao.findAll().toString().contains(demo.toString()));
        assertTrue(roleDao.findAll().toString().contains(role.toString()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Role role = roleDao.find("role");
        Date createDate = new Date();
        role.setCreateDate(createDate);
        roleDao.update(role);
        Role actual = roleDao.find("role");
        assertEquals(createDate, actual.getCreateDate());
    }

}
