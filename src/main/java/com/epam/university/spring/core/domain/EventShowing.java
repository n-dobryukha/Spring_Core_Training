package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

import java.util.Date;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 12:23
 */
public class EventShowing implements Storable<Long>, RetreiveFieldsValues {

    private Long id;
    private Event event;
    private Auditorium auditorium;
    private Date date;

    public EventShowing() {
    }

    public EventShowing(Event event, Auditorium auditorium, Date date) {
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] {this.event.getId(), this.auditorium.getId(), this.date};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventShowing that = (EventShowing) o;

        if (!id.equals(that.id)) return false;
        if (!event.equals(that.event)) return false;
        if (!auditorium.equals(that.auditorium)) return false;
        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + event.hashCode();
        result = 31 * result + auditorium.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
