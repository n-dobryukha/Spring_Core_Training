package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Event implements Storable<Long> {
    private Long id;
    private String name;
    private double basePrice;
    private EventRating rating;

    public Event(String name, double basePrice, EventRating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
