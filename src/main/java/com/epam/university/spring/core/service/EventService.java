package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class EventService {
    private GenericDao<Long, Event> eventDao;

    public EventService(GenericDao<Long, Event> eventDao) {
        this.eventDao = eventDao;
    }

    public Event create(String name, Date date, double price, EventRating rating) {
        Event event = new Event(name, date, price, rating);
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
}
