package com.hisense.development.dao;

import com.hisense.development.base.persistence.RoleMapper;
import com.hisense.development.base.domain.Role;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class RoleDaoTest extends TestCase {

    @Autowired
    RoleMapper roleDao;
    private Role role;

    @Before
    public void beforeTest()  {
        roleDao.deleteAll();
        role= new Role("role", true);
        roleDao.create(role);
    }

    @Test
    public void testCreateUser()  {
        int size = roleDao.findAll().size();
        roleDao.create(new Role("role1", true));
        assertEquals(size + 1, roleDao.findAll().size());
    }

    @Test
    public void testDelete()  {
        Role demo = new Role("demo", true);
        roleDao.create(demo);
        List<Long> collect = roleDao.findAll().stream().map(s -> s.getId()).collect(Collectors.toList());
        boolean b = roleDao.delete(collect);
        assertTrue(b);
    }

    @Test
    public void testFindByName()  {
        Role role = roleDao.find(this.role.getName());
        assertEquals(this.role.getName(), role.getName());
        assertEquals(this.role.getAvailable(), role.getAvailable());
        assertTrue(role.getId() >= 0);
    }

    @Test
    public void testFindAll()  {
        Role demo = new Role("demo", true);
        roleDao.create(demo);
        assertTrue(roleDao.findAll().size() == 2);
    }

    @Test
    public void testUpdateUser()  {
        Role role = roleDao.find("role");
        role.setAvailable(false);
        roleDao.update(role);
        Role actual = roleDao.find("role");
        assertFalse(actual.getAvailable());
    }

}
