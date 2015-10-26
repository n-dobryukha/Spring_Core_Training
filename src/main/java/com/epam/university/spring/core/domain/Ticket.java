package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Ticket implements Storable<Long> {
    private Long id;
    private User user;
    private Event event;
    private Auditorium auditorium;
    private int seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
}
