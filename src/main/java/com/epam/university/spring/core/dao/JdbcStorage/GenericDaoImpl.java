package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.dao.Storable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 17:46
 */
public class GenericDaoImpl<PK, T extends Storable<PK>> implements GenericDao<PK, T> {

    private JdbcTemplate jdbcTemplate;
    private Class<T> type;

    public GenericDaoImpl(JdbcTemplate jdbcTemplate, Class<T> type) {
        this.jdbcTemplate = jdbcTemplate;
        this.type = type;
    }

    @Override
    public PK create(T object) {
        return null;
    }

    @Override
    public T get(PK id) {
        return null;
    }

    @Override
    public Collection<T> get() {
        return null;
    }

    @Override
    public void update(T object) {

    }

    @Override
    public void delete(T object) {

    }
}
