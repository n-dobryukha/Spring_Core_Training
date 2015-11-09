package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;

import java.util.Date;
import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 10:22
 */
public interface UserService {
    User register(String name, String email, Date birthday);
    void remove(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByName(String name);
    boolean isUserRegistered(User user);
    List<Ticket> getBookedTickets(User user);
}
