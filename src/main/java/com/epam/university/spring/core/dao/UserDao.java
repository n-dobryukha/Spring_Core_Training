package com.epam.university.spring.core.dao;

import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 27.10.2015.
 */
public interface UserDao extends GenericDao<Long, User> {

    User getUserByName(String name);
    User getUserByEmail(String email);
    List<Ticket> getBookedTickets(User user);
}
