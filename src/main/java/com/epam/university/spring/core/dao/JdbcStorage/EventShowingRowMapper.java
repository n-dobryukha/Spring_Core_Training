package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.domain.EventShowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikita Dobriukha
 * Date: 16.11.2015.
 */
public class EventShowingRowMapper implements RowMapper<EventShowing> {
    @Autowired
    private EventDaoImpl eventDao;

    @Autowired
    private AuditoriumDaoImpl auditoriumDao;

    @Override
    public EventShowing mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventShowing eventShowing = new EventShowing();
        eventShowing.setId(rs.getLong("ID"));
        eventShowing.setEvent(eventDao.get(rs.getLong("EVENT_ID")));
        eventShowing.setAuditorium(auditoriumDao.get(rs.getLong("AUDITORIUM_ID")));
        eventShowing.setDate(rs.getDate("DATE"));
        return eventShowing;
    }
}
