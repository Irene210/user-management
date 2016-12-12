package com.hisense.development.service;

import com.hisense.development.entity.User;
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

import javaslang.collection.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserServiceTest extends TestCase {
    @Autowired
    UserService userService;
    @Autowired
    PasswordHelper passwordHelper;
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
        Either<Exception, Boolean> irene2 = userService.create(new User("irene2", "123"));
        assertTrue(irene2.get());
        User actual = userService.find("irene2").get();
        assertEquals("irene2", actual.getName());
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
        List<Long> map = userService.findAll().map(s -> s.getId());
        assertTrue(userService.delete(map).get());
    }

    @Test
    public void testDeleteNotExistedUser() {
        List<Long> map = userService.findAll().map(s -> s.getId());
        map.append(new Long(111));
        userService.delete(map);
    }

    @Test
    public void testUpdateUser() {
        Option<User> byUsername = userService.find(irene.getName());
        User user = byUsername.get();
        user.setSex("woman");
        user.setLocked(true);
        userService.update(user);
        User actual = userService.find(irene.getName()).get();
        assertEquals("woman", actual.getSex());
        assertTrue(actual.getLocked());

    }

    @Test
    public void testFindNotExistedUser() {
        Option<User> userOption = userService.find("");
        assertTrue(userOption.equals(Option.none()));
    }

}
