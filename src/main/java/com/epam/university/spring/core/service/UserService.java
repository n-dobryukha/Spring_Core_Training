package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.User;

import java.util.UUID;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class UserService {
    private GenericDao<Long, User> userDao;

    public UserService(GenericDao<Long, User> userDao) {
        this.userDao = userDao;
    }

    public User register(String name, String email) {
        User user = new User(name, email);
        userDao.create(user);
        return user;
    }

    public void remove(User user) {
        userDao.delete(user);
    }

    public User getById(Long id) {
        return userDao.get(id);
    }

    public User getUserByEmail(String email) {
        for (User user: userDao.get()) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : userDao.get()) {
            if (name.equals(user.getName())) return user;
        }
        return null;
    }
}
