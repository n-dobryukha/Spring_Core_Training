package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.dao.Storable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public abstract class GenericDaoImpl<PK, T extends Storable<PK>> implements GenericDao<PK, T> {

    private Map<PK, T> storage;

    public GenericDaoImpl(Map<PK, T> storage) {
        this.storage = storage;
    }

    public PK create(T object) {
        PK key = object.getId();
        storage.put(key, object);
        return key;
    }

    public T get(PK id) {
        return storage.get(id);
    }

    public Collection<T> get() {
        return storage.values();
    }

    public void update(T object) {
        storage.put(object.getId(), object);
    }

    public void delete(T object) {
        storage.remove(object.getId());
    }
}
