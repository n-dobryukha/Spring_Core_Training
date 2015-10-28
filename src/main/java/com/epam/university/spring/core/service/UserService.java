package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.UserDao;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class UserService implements ApplicationContextAware {

    private ApplicationContext appContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public User register(String name, String email, Date birthday) {
        User user = (User) appContext.getBean("user");
        user.setName(name);
        user.setEmail(email);
        user.setBirthday(birthday);
        userDao.create(user);
        return user;
    }

    public void remove(User user) {
        userDao.delete(user);
    }

    public User getUserById(Long id) {
        return userDao.get(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public boolean isUserRegistered(User user) {
        return userDao.get(user.getId()) != null;
    }

    public List<Ticket> getBookedTickets(User user) {
        return userDao.getBookedTickets(user);
    }
}
