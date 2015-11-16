package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikita Dobriukha
 * Date: 16.11.2015.
 */
public class TicketRowMapper implements RowMapper<Ticket> {
    @Autowired
    UserDaoImpl userDao;

    @Autowired
    EventShowingDaoImpl eventShowingDao;

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("ID"));
        ticket.setUser(userDao.get(rs.getLong("USER_ID")));
        ticket.setEventShowing(eventShowingDao.get(rs.getLong("EVENT_SHOWING_ID")));
        ticket.setSeat(rs.getInt("SEAT"));
        ticket.setCost(rs.getDouble("COST"));
        return ticket;
    }
}
