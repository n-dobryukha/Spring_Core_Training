package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.UserDao;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 17:55
 */
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, User.class);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return null;
    }
}
