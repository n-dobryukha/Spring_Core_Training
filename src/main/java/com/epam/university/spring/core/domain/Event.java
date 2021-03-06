package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Event implements Storable<Long>, RetreiveFieldsValues {
    private Long id;
    private String name;
    private double basePrice;
    private EventRating rating;

    public Event() {
    }

    public Event(String name, double basePrice, EventRating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] {getName(), getBasePrice(), getRating().toString()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (Double.compare(event.basePrice, basePrice) != 0) return false;
        if (!id.equals(event.id)) return false;
        if (!name.equals(event.name)) return false;
        return rating == event.rating;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rating.hashCode();
        return result;
    }
}
