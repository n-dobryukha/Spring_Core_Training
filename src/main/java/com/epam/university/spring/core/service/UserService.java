package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class UserService {

    @Autowired
    @Qualifier("userDao")
    private GenericDao<Long, User> userDao;

    @Autowired
    @Qualifier("ticketDao")
    private GenericDao<Long, Ticket> ticketDao;

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

    public List<Ticket> getBookedTickets(User user) {
        List<Ticket> bookedTickets = new LinkedList<Ticket>();
        for (Ticket ticket: ticketDao.get()) {
            if (user.equals(ticket.getUser())) bookedTickets.add(ticket);
        }
        return bookedTickets;
    }
}
