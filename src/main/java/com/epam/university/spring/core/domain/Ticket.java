package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Ticket implements Storable<Long> {
    private Long id;
    private User user;
    private EventShowing eventShowing;
    private int seat;
    private double cost;

    public Ticket(User user, EventShowing eventShowing, int seat, double cost) {
        this.user = user;
        this.eventShowing = eventShowing;
        this.seat = seat;
        this.cost = cost;
    }

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
