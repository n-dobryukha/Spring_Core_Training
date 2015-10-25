package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Event;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Nikita Dobriukha
 * Date: 26.10.2015.
 */
public class EventDao extends GenericDaoImpl<Long, Event> {

    public EventDao(Map<Long, Event> storage) {
        super(storage);
    }

    @Override
    public Long create(Event object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }
}
