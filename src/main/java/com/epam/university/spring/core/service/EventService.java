package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.EventShowing;

import java.util.Date;
import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 10:24
 */
public interface EventService {
    Event create(String name, double price, EventRating rating);
    void remove(Event event);
    Event getEventById(Long id);
    Event getEventByName(String name);
    List<Event> getAll();
    EventShowing assignAuditorium(Event event, Auditorium auditorium, Date date);
    EventShowing getEventShowing(Event event, Date date);
}
