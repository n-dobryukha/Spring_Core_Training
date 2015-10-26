package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Auditorium;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 26.10.2015.
 */
public class AuditoriumDao extends GenericDaoImpl<Long, Auditorium> {

    public AuditoriumDao(@Qualifier("auditoriumStorage") Map<Long, Auditorium> storage) {
        super(storage);
    }

}
