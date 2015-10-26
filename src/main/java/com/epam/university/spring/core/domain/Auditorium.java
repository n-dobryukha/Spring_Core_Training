package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Auditorium implements Storable<Long> {
    private Long id;
    private String name;
    private int numberOfSeats;
    private List<Integer> vipSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auditorium(String name, int numberOfSeats, List<Integer> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    public boolean isVIP(Integer number) {
        return vipSeats.contains(number);
    }
}
