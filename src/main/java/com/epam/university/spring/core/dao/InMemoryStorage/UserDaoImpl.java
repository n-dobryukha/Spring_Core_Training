package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.dao.TicketDao;
import com.epam.university.spring.core.dao.UserDao;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired
    @Qualifier("ticketDao")
    private TicketDao ticketDao;

    public UserDaoImpl(@Qualifier("userStorage") Map<Long, User> storage) {
        super(storage);
    }

    @Override
    public Long create(User object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }

    public User getUserByName(String name) {
        for (User user: get()) {
            if (name.equals(user.getName())) return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user: get()) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }
}
