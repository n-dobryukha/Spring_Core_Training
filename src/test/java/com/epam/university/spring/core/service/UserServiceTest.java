package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.JdbcStorage.UserDaoImpl;
import com.epam.university.spring.core.domain.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Calendar;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-28
 * Time: 09:24
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class UserServiceTest extends TestCase {

    @Autowired
    UserService userService;

    @Autowired
    UserDaoImpl userDao;

    @After
    public void tearDown() throws Exception {
        userDao.delete();
    }

    @Test
    public void testInjectUserService() {
        assertNotNull(userService);
    }

    private User createUser() {
        String DUMMY_USER_NAME = "John Doe";
        String DUMMY_USER_EMAIL = "j.doe@example.com";
        User user = userService.register(DUMMY_USER_NAME, DUMMY_USER_EMAIL, new Date(1985, Calendar.JANUARY, 1));
        assertNotNull(user);
        assertTrue(userService.isUserRegistered(user));
        return user;
    }

    @Test
    public void testCreate() {
        createUser();
    }

    @Test
    public void testRemove() {
        User user = createUser();
        userService.remove(user);
        assertFalse(userService.isUserRegistered(user));
    }

    @Test
    public void testGetUserByAttribute() {
        User user = createUser();
        assertEquals(user, userService.getUserById(user.getId()));
        assertEquals(user, userService.getUserByName(user.getName()));
        assertEquals(user, userService.getUserByEmail(user.getEmail()));
    }

}
