package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.dao.EventCounterDao;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventCounter;

import java.util.Map;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-06
 * Time: 16:24
 */
public class EventCounterDaoImpl extends GenericDaoImpl<Event, EventCounter> implements EventCounterDao {

    public EventCounterDaoImpl(Map<Event, EventCounter> storage) {
        super(storage);
    }
}
