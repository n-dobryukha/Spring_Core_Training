package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.User;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class UserDao extends GenericDaoImpl<Long, User> {

    public UserDao(Map<Long, User> storage) {
        super(storage);
    }

    @Override
    public Long create(User object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }
}
