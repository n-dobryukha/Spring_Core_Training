package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.EventShowingDao;
import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.EventShowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikita Dobriukha
 * Date: 15.11.2015.
 */
public class EventShowingDaoImpl extends GenericDaoImpl<EventShowing> implements EventShowingDao {

    public EventShowingDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, EventShowing.class,
                "INSERT INTO EVENT_SHOWING (EVENT_ID, AUDITORIUM_ID, DATE) VALUES(?,?,?)",
                "SELECT * FROM EVENT_SHOWING",
                "UPDATE EVENT_SHOWING SET EVENT_ID = ?, AUDITORIUM_ID = ?, DATE = ? WHERE ID = ?",
                "DELETE FROM EVENT_SHOWING");
    }

    public EventShowingDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<EventShowing> rowMapper) {
        super(jdbcTemplate, EventShowing.class,
                "INSERT INTO EVENT_SHOWING (EVENT_ID, AUDITORIUM_ID, DATE) VALUES(?,?,?)",
                "SELECT * FROM EVENT_SHOWING",
                "UPDATE EVENT_SHOWING SET EVENT_ID = ?, AUDITORIUM_ID = ?, DATE = ? WHERE ID = ?",
                "DELETE FROM EVENT_SHOWING",
                rowMapper);
    }

    public void createTable() {
        String createString = "CREATE TABLE EVENT_SHOWING "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT EVENT_SHOWING_PK PRIMARY KEY, "
                +  " EVENT_ID INT CONSTRAINT EVENT_SHOWING_FK1 REFERENCES EVENTS (ID) ON DELETE CASCADE, "
                +  " AUDITORIUM_ID INT CONSTRAINT EVENT_SHOWING_FK2 REFERENCES AUDITORIUMS (ID), "
                +  " DATE DATE NOT NULL)";
        getJdbcTemplate().execute(createString);
    }
}
