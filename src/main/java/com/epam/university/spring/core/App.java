package com.epam.university.spring.core;

import com.epam.university.spring.core.dao.UserDao;
import com.epam.university.spring.core.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/app.xml");
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        User user = new User("TEST", "test@example.com", new Date(84, 1, 1));
        long idx = userDao.create(user);
        List<User> users = (List<User>) userDao.get();
        userDao.delete(user);
        List<User> users2 = (List<User>) userDao.get();
        ctx.close();
    }
}
