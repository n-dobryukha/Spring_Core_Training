package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class User implements Storable<Long> {
    private Long id;
    private String name;
    private String email;
    private List<Ticket> bookedTickest;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
