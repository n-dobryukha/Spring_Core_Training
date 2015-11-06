package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.EventDao;
import com.epam.university.spring.core.dao.EventShowingDao;
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
    private EventDao eventDao;

    @Autowired
    @Qualifier("eventShowingDao")
    private EventShowingDao eventShowingDao;

    public Event create(String name, double price, EventRating rating) {
        Event event = new Event(name, price, rating);
        eventDao.create(event);
        return event;
    }

    public void remove(Event event) {
        eventDao.delete(event);
    }

    public Event getEventById(Long id) {
        return eventDao.get(id);
    }

    public Event getEventByName(String name) {
        return eventDao.getEventByName(name);
    }

    public List<Event> getAll() {
        return new ArrayList<>(eventDao.get());
    }

    public EventShowing assignAuditorium(Event event, Auditorium auditorium, Date date) {
        EventShowing eventShowing = new EventShowing(event, auditorium, date);
        eventShowingDao.create(eventShowing);
        return eventShowing;
    }

    public EventShowing getEventShowing(Event event, Date date) {
        for (EventShowing eventShowing: eventShowingDao.get()) {
            if (event.equals(eventShowing.getEvent()) && date.equals(eventShowing.getDate())) return eventShowing;
        }
        return null;
    }
}
