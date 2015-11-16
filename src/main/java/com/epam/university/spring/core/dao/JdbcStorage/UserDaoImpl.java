package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.UserDao;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 17:55
 */
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, User.class,
                "INSERT INTO USERS (NAME, EMAIL, BIRTHDAY) VALUES(?,?,?)",
                "SELECT * FROM USERS",
                "UPDATE USERS SET NAME = ?, EMAIL = ?, BIRTHDAY = ? WHERE ID = ?",
                "DELETE FROM USERS"
                );
    }

    public void createTable() {
        String createString = "CREATE TABLE USERS  "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT USER_PK PRIMARY KEY, "
                +  " NAME VARCHAR(50) NOT NULL, "
                +  " EMAIL VARCHAR(50) NOT NULL, "
                +  " BIRTHDAY DATE NOT NULL)";
        getJdbcTemplate().execute(createString);
    }

    @Override
    public User getUserByName(String name) {
        return getJdbcTemplate().queryForObject("SELECT * FROM USERS WHERE NAME = ?",
                new Object[] {name}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUserByEmail(String email) {
        return getJdbcTemplate().queryForObject("SELECT * FROM USERS WHERE EMAIL = ?",
                new Object[] {email}, new BeanPropertyRowMapper<>(User.class));
    }

}
