package com.epam.university.spring.core.dao;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public interface GenericDao<T, PK> {
    /** Create object */
    PK create(T object);

    /** Return object by id */
    T get(PK id);

    /** Return all objects */
    List<T> get();

    /** Update object */
    void update(T object);

    /** Delete object */
    void delete(T object);
}
