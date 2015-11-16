package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Ticket implements Storable<Long>, RetreiveFieldsValues {
    private Long id;
    private User user;
    private EventShowing eventShowing;
    private int seat;
    private double cost;

    public Ticket() {
    }

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

    public void setUser(User user) {
        this.user = user;
    }

    public EventShowing getEventShowing() {
        return eventShowing;
    }

    public void setEventShowing(EventShowing eventShowing) {
        this.eventShowing = eventShowing;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] {user.getId(), eventShowing.getId(), seat, cost};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (seat != ticket.seat) return false;
        if (Double.compare(ticket.cost, cost) != 0) return false;
        if (!id.equals(ticket.id)) return false;
        if (!user.equals(ticket.user)) return false;
        return eventShowing.equals(ticket.eventShowing);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + eventShowing.hashCode();
        result = 31 * result + seat;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
