package com.hisense.development.service;

import com.hisense.development.base.domain.Role;
import com.hisense.development.user.service.RoleService;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.*;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class RoleServiceTest {
    @Autowired
    RoleService roleService;
   
    private Role role;

    @Before
    public void beforeTest(){
        roleService.deleteAll();
        role = new Role("role", true);
        roleService.create(role);
    }

    @After
    public void afterTest() {
        roleService.deleteAll();
    }

    @Test
    public void testCreateUser() {
        Either<Exception, Boolean> irene2 = roleService.create(new Role("irene2", true));
        assertTrue(irene2.get());
        Role actual = roleService.find("irene2").get();
        assertEquals("irene2", actual.getName());
        assertFalse(actual.getId().equals(""));
    }

    @Test(expected = Exception.class)
    public void testCreateEmptyUser() {
        roleService.create(new Role()).get();
    }
    @Test(expected = Exception.class)
    public void testCreateExistedUser() {
        roleService.create(roleService.find("role").get()).get();
    }


    @Test
    public void testDeleteUser() {
        List<Long> map = roleService.findAll().map(s -> s.getId());
        assertTrue(roleService.delete(map).get());
    }

    @Test
    public void testDeleteNotExistedUser() {
        List<Long> map = roleService.findAll().map(s -> s.getId());
        map.append(new Long(111));
        roleService.delete(map);
    }

    @Test
    public void testUpdateUser() {
        Role role = roleService.find(this.role.getName()).get();
        role.setAvailable(false);
        roleService.update(role);
        Role actual = roleService.find(this.role.getName()).get();
        assertFalse(actual.getAvailable());

    }

    @Test
    public void testFindNotExistedUser() {
        Option<Role> userOption = roleService.find("");
        assertTrue(userOption.equals(Option.none()));
    }
}
