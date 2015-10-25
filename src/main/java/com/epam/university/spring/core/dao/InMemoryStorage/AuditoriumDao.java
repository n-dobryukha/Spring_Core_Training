package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Auditorium;

import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 26.10.2015.
 */
public class AuditoriumDao extends GenericDaoImpl<Long, Auditorium> {

    public AuditoriumDao(Map<Long, Auditorium> storage) {
        super(storage);
    }
}
