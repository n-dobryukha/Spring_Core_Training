package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.EventShowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class EventService {

    @Autowired
    @Qualifier("eventDao")
    private GenericDao<Long, Event> eventDao;

    @Autowired
    @Qualifier("eventShowingDao")
    private GenericDao<Long, EventShowing> eventShowingDao;

    public Event create(String name, double price, EventRating rating) {
        Event event = new Event(name, price, rating);
        eventDao.create(event);
        return event;
    }

    public void remove(Event event) {
        eventDao.delete(event);
    }

    public Event getByName(String name) {
        for (Event event: eventDao.get()) {
            if (name.equals(event.getName())) return event;
        }
        return null;
    }

    public List<Event> getAll() {
        return new ArrayList<Event>(eventDao.get());
    }

    public EventShowing assignAuditorium(Event event, Auditorium auditorium, Date date) {
        EventShowing eventShowing = new EventShowing(event, auditorium, date);
        eventShowingDao.create(eventShowing);
        return eventShowing;
    }

    public EventShowing getEventShowing() {

        return null;
    }
}
