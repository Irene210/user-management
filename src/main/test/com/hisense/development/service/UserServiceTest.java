package com.hisense.development.service;

import com.hisense.development.entity.User;
import javaslang.control.Option;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javaslang.collection.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserServiceTest extends TestCase {
    @Autowired
    UserService userService;
    private User irene;

    @Before
    public void beforeTest(){
        userService.deleteAll();
        irene = new User("irene", "123");
        userService.create(irene);
    }

    @After
    public void afterTest() {
        userService.deleteAll();
    }

    @Test
    public void testCreateUser() {
        assertTrue( userService.create(new User("irene2", "123")));
        User actual = userService.findByUsername("irene2").get();
        assertEquals("irene2", actual.getUsername());
        assertFalse(actual.getId().equals(""));
    }

    @Test
    public void testCreateEmptyUser() {
        assertFalse(userService.create(new User()));
    }
    @Test
    public void testCreateExistedUser() {
        assertFalse(userService.create(new User("irene", "123")));
    }

    @Test
    public void testDeleteUser() {
        List<String> list=List.of(irene.getUsername());
        assertTrue(userService.delete(list));
    }

    @Test
    public void testDeleteNotExistedUser() {
        List<String> list=List.of(irene.getUsername(),"");
        assertFalse(userService.delete(list));
    }

    @Test
    public void testUpdateUser() {
        User user = userService.findByUsername(irene.getUsername()).get();
        user.setSex("woman");
        user.setLocked(true);
        userService.update(user);
        User actual = userService.findByUsername(irene.getUsername()).get();
        assertEquals("woman", actual.getSex());
        assertTrue(actual.getLocked());
    }

    @Test
    public void testFindNotExistedUser() {
        Option<User> userOption = userService.findByUsername("");
        assertTrue(userOption.equals(Option.none()));
    }

}
