package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Auditorium implements Storable<Long>, RetreiveFieldsValues {
    private Long id;
    private String name;
    private int numberOfSeats;
    private List<Integer> vipSeats;

    public Auditorium() {
    }

    public Auditorium(String name, int numberOfSeats, List<Integer> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(List<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }

    public boolean isVIP(Integer number) {
        return vipSeats.contains(number);
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] { getName(), getNumberOfSeats(), getVipSeats().toString() };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;

        if (numberOfSeats != that.numberOfSeats) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return vipSeats.equals(that.vipSeats);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + numberOfSeats;
        result = 31 * result + vipSeats.hashCode();
        return result;
    }
}
