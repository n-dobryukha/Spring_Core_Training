package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

import java.util.Calendar;
import java.sql.Date;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class User implements Storable<Long>, RetreiveFieldsValues {
    private Long id;
    private String name;
    private Date birthday;
    private String email;

    public User() {
    }

    public User(String name, String email, Date birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isBirthdayToday() {
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(getBirthday());
        return  (today.get(Calendar.DAY_OF_MONTH) == birthday.get(Calendar.DAY_OF_MONTH)) &&
                (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH));
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] {getName(), getEmail(), getBirthday()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        return birthday.equals(user.birthday);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
