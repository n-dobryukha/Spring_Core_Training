package com.epam.university.spring.core.dao;

import com.epam.university.spring.core.domain.Event;

/**
 * Created by Nikita Dobriukha
 * Date: 27.10.2015.
 */
public interface EventDao extends GenericDao<Long, Event> {

    Event getEventByName(String name);
}
