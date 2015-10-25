package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Ticket implements Storable {
    private Long id;
    private Event event;
    private Auditorium auditorium;
    private int seat;

    public Object getId() {
        return null;
    }

    public void setId(Object id) {

    }
}
