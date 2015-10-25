package com.epam.university.spring.core.dao;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public interface Storable<PK> {
    /** Return Id of object */
    PK getId();

    /** Insert object Id */
    void setId(PK id);
}
