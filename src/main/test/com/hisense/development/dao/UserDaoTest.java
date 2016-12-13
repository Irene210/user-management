package com.hisense.development.dao;

import com.hisense.development.base.persistence.DepartmentMapper;
import com.hisense.development.base.persistence.RoleMapper;
import com.hisense.development.base.persistence.UserMapper;
import com.hisense.development.base.domain.Department;
import com.hisense.development.base.domain.Role;
import com.hisense.development.base.domain.User;
import com.hisense.development.base.domain.UserRole;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserDaoTest extends TestCase {
    @Autowired
    UserMapper userDao;
    @Autowired
    RoleMapper roleDao;
    @Autowired
    DepartmentMapper departmentDao;

    private  User irene;

    @Before
    public void beforeTest() {
        userDao.deleteAll();
        roleDao.deleteAll();
         irene = new User("irene","123");
        userDao.create(irene);
    }

    @Test
    public void testCreateUser() {
        int size = userDao.findAll().size();
        userDao.create(new User("demo","123"));
        assertEquals(size+1,userDao.findAll().size());
    }

    @Test
    public void testDelete() {
        User demo = new User("demo", "123");
        userDao.create(demo);
        List<Long> collect = userDao.findAll().stream().map(s -> s.getId()).collect(Collectors.toList());
        boolean b = userDao.delete(collect);
        assertTrue(b);
    }

    @Test
    public void testFindByName() {
        User user = userDao.find(irene.getUsername());
        assertEquals(irene.getUsername(),user.getUsername());
        assertEquals(irene.getPassword(),user.getPassword());
        assertTrue(user.getId()>=0);
    }

    @Test
    public void testFindById() {
        User user = userDao.find(irene.getUsername());
        User user1 = userDao.find(user.getId());
        assertEquals(irene.getUsername(),user1.getUsername());
        assertEquals(irene.getPassword(),user1.getPassword());
        assertEquals(user.getId(),user1.getId());
    }

    @Test
    public void testFindAll() {
        User demo = new User("demo", "123");
        userDao.create(demo);
        assertTrue(userDao.findAll().size()==2);
    }

    @Test
    public void testUpdateUser() {
        User irene = userDao.find("irene");
        irene.setSex("woman");
        irene.setDisplayNum(1);
        userDao.update(irene);
        User user = userDao.find("irene");
        assertTrue(user.getSex().equals("woman"));
        assertTrue(user.getDisplayNum()==1);
    }

    @Test
    public void testCorrelationRoles() {
        Long id = new Long(3);
        UserRole userRole = new UserRole(id, id);
        assertTrue(userDao.correlationRoles(userRole));
        assertTrue(userDao.uncorrelationRoles(id));
    }

    @Test
    public void testFindRoles() {
        roleDao.create(new Role("role",true));
        roleDao.create(new Role("role1",true));
        roleDao.create(new Role("role2",true));
        User irene = userDao.find("irene");
        assertTrue(userDao.correlationRoles(new UserRole(irene.getId(), roleDao.find("role").getId())));
        assertTrue(userDao.correlationRoles(new UserRole(irene.getId(), roleDao.find("role1").getId())));
        assertTrue(userDao.correlationRoles(new UserRole(irene.getId(), roleDao.find("role2").getId())));
        Set<Role> roles = userDao.findRoles(irene.getId());
        assertTrue(roles.size()==3);
    }

    @Test
    public void testCreateUserWithDepartment() {
        int size = userDao.findAll().size();
        Department department = new Department();
        department.setName("department");
        departmentDao.create(department);
        User demo = new User("demo", "123");
        Department department1 = departmentDao.findAll().get(0);
        demo.setDepartment(department1);
        userDao.create(demo);
        assertEquals(userDao.find("demo").getDepartment().getName(),department1.getName());
        assertEquals(userDao.find("demo").getDepartment().getId(),department1.getId());
    }
}
