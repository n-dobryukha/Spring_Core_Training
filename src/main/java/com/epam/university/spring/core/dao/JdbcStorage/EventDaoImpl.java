package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.EventDao;
import com.epam.university.spring.core.domain.Event;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-10
 * Time: 18:36
 */
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

    public EventDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Event.class,
                "INSERT INTO EVENTS (NAME, BASEPRICE, RATING) VALUES(?,?,?)",
                "SELECT * FROM EVENTS",
                "UPDATE EVENTS SET NAME = ?, BASEPRICE = ?, RATING = ? WHERE ID = ?",
                "DELETE FROM EVENTS"
        );
    }

    public void createTable() {
        String createString = "CREATE TABLE EVENTS  "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT EVENT_PK PRIMARY KEY, "
                +  " NAME VARCHAR(50) NOT NULL, "
                +  " BASEPRICE DOUBLE NOT NULL, "
                +  " RATING VARCHAR(4) CONSTRAINT RATING_CH CHECK (RATING IN ('HIGH', 'MID', 'LOW')))";
        getJdbcTemplate().execute(createString);
    }

    @Override
    public Event getEventByName(String name) {
        return getJdbcTemplate().queryForObject("SELECT * FROM EVENTS WHERE NAME = ?",
                new Object[] {name}, new BeanPropertyRowMapper<>(Event.class));
    }
}
