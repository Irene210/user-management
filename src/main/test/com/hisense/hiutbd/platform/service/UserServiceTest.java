package com.hisense.hiutbd.platform.service;

import com.hisense.hiutbd.platform.base.domain.Role;
import com.hisense.hiutbd.platform.base.domain.User;
import com.hisense.hiutbd.platform.user.service.RoleService;
import com.hisense.hiutbd.platform.user.service.UserService;
import com.hisense.hiutbd.platform.user.serviceimpl.PasswordHelper;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javaslang.collection.Set;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest extends TestCase {
    @Autowired
    UserService userService;
    @Autowired
    PasswordHelper passwordHelper;
    @Autowired
    RoleService roleService;
    private User irene;

    @Before
    public void beforeTest(){
        userService.deleteAll();
        irene = new User("1","irene", "123");
        userService.create(irene);
    }

    @After
    public void afterTest() {
        userService.deleteAll();
    }

    @Test
    public void testCreateUser() {
        Either<Exception, Boolean> irene2 = userService.create(new User("2","irene2", "123"));
        assertTrue(irene2.get());
        User actual = userService.find("irene2").get();
        assertEquals("irene2", actual.getUsername());
        assertFalse(actual.getId().equals(""));
    }

    @Test(expected = Exception.class)
    public void testCreateEmptyUser() {
        userService.create(new User()).get();
    }
    @Test(expected = Exception.class)
    public void testCreateExistedUser() {
         userService.create(userService.find("irene").get()).get();
    }


    @Test
    public void testDeleteUser() {
        List<String> map = userService.findAll().map(s -> s.getId());
        assertTrue(userService.delete(map).get());
    }

    @Test
    public void testDeleteNotExistedUser() {
        List<String> map = userService.findAll().map(s -> s.getId());
        map.append("");
        userService.delete(map);
    }

    @Test
    public void testUpdateUser() {
        Option<User> byUsername = userService.find(irene.getUsername());
        User user = byUsername.get();
        user.setSex("woman");
        user.setLocked(true);
        userService.update(user);
        User actual = userService.find(irene.getUsername()).get();
        assertEquals("woman", actual.getSex());
        assertTrue(actual.getLocked());

    }

    @Test
    public void testFindNotExistedUser() {
        Option<User> userOption = userService.find("");
        assertTrue(userOption.equals(Option.none()));
    }

    @Test
    public void testCorrelationRoles() {
        roleService.create(new Role("1","role",true));
        roleService.create(new Role("2","role1",true));
        roleService.create(new Role("3","role2",true));
        Set<String> roleIds = roleService.findAll().toStream().map(s -> s.getId()).toSet();
        Boolean correlationRoles = userService.correlationRoles(userService.find("irene").get().getId(), roleIds);
        assertTrue(correlationRoles);
        Boolean uncorrelationRoles = userService.uncorrelationRoles(userService.find("irene").get().getId()).get();
        assertTrue(uncorrelationRoles);
    }

    @Test
    public void testFindRoles() {
        roleService.create(new Role("1","role",true));
        roleService.create(new Role("1","role1",true));
        roleService.create(new Role("1","role2",true));
        Set<String> roleIds = roleService.findAll().toStream().map(s -> s.getId()).toSet();
        String userId = userService.find("irene").get().getId();
        userService.correlationRoles(userId, roleIds);
        assertTrue(userService.findRoles(userId).get().size()==3);
    }

}
