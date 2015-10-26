package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Schedule;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;
import java.util.UUID;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 12:25
 */
public class ScheduleDao extends GenericDaoImpl<Long, Schedule> {

    public ScheduleDao(@Qualifier("scheduleStorage") Map<Long, Schedule> storage) {
        super(storage);
    }

    @Override
    public Long create(Schedule object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }

}
