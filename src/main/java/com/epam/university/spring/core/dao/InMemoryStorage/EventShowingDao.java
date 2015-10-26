package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventShowing;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 12:25
 */
public class EventShowingDao extends GenericDaoImpl<Long, EventShowing> {

    public EventShowingDao(@Qualifier("eventShowingStorage") Map<Long, EventShowing> storage) {
        super(storage);
    }

    @Override
    public Long create(EventShowing object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }

    public EventShowing getShowing(Event event, Auditorium auditorium, Date date) {
        for (EventShowing eventShowing : get()) {
            if (event.equals(eventShowing.getEvent()) && auditorium.equals(eventShowing.getAuditorium()) &&
                date.equals(eventShowing.getDate())) {
                return eventShowing;
            }
        }
        return null;
    }

}
