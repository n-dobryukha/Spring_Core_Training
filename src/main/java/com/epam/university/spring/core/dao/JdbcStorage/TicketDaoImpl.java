package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.TicketDao;
import com.epam.university.spring.core.domain.EventShowing;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 16.11.2015.
 */
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    public TicketDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Ticket> rowMapper) {
        super(jdbcTemplate, Ticket.class,
                "INSERT INTO TICKETS (USER_ID, EVENT_SHOWING_ID, SEAT, COST) VALUES(?,?,?,?)",
                "SELECT * FROM TICKETS",
                "UPDATE TICKETS SET USER_ID = ?, EVENT_SHOWING_ID = ?, SEAT = ?, COST = ? WHERE ID = ?",
                "DELETE FROM TICKETS",
                rowMapper);
    }

    public void createTable() {
        String createString = "CREATE TABLE TICKETS  "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT TICKET_PK PRIMARY KEY, "
                +  " USER_ID INT CONSTRAINT TICKET_FK1 REFERENCES USERS (ID) ON DELETE CASCADE, "
                +  " EVENT_SHOWING_ID INT CONSTRAINT TICKET_FK2 REFERENCES EVENT_SHOWING (ID), "
                +  " SEAT INT NOT NULL, "
                +  " COST DOUBLE NOT NULL)";
        getJdbcTemplate().execute(createString);
    }

    @Override
    public List<Ticket> getTicketsByEventShowing(EventShowing eventShowing) {
        return getJdbcTemplate().query("SELECT * FROM TICKETS WHERE EVENT_SHOWING_ID = ?",
                new Object[] {eventShowing.getId()}, getRowMapper());
    }

    public List<Ticket> getBookedTickets(User user) {
        return getJdbcTemplate().query("SELECT * FROM TICKETS WHERE USER_ID = ?",
                new Object[] {user.getId()}, getRowMapper());
    }
}
