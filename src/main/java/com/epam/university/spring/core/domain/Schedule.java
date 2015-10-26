package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

import java.util.Date;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 12:23
 */
public class Schedule implements Storable<Long> {

    private Long id;
    private Event event;
    private Auditorium auditorium;
    private Date date;

    public Schedule(Event event, Auditorium auditorium, Date date) {
        this.event = event;
        this.auditorium = auditorium;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
