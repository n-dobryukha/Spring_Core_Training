package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class User implements Storable<Long> {
    private Long id;
    private String name;
    private String email;
    private Date birthday;

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

    public Date getBirthday() {
        return birthday;
    }

    public boolean isBirthday() {
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(getBirthday());
        return  (today.get(Calendar.DAY_OF_MONTH) == birthday.get(Calendar.DAY_OF_MONTH)) &&
                (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH));
    }
}
