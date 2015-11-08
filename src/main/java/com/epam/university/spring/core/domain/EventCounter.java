package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-06
 * Time: 15:57
 */
public class EventCounter implements Storable<Event> {

    private Event id;
    private long countAccessByName;
    private long countPriceQueried;
    private long countTicketsBooked;

    public EventCounter() {
        countAccessByName = 0;
        countPriceQueried = 0;
        countTicketsBooked = 0;
    }

    @Override
    public Event getId() {
        return id;
    }

    @Override
    public void setId(Event id) {
        this.id = id;
    }

    public long getCountAccessByName() {
        return countAccessByName;
    }

    public long getCountPriceQueried() {
        return countPriceQueried;
    }

    public long getCountTicketsBooked() {
        return countTicketsBooked;
    }

    public void incCounterAccessByName() {
        this.countAccessByName++;
    }

    public void incCounterPriceQueried() {
        this.countPriceQueried++;
    }

    public void incCounterTicketsBooked() {
        this.countTicketsBooked++;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((EventCounter) o).id);
    }
}
