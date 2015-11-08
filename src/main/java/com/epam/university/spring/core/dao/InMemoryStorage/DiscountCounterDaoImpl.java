package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.dao.DiscountCounterDao;
import com.epam.university.spring.core.domain.DiscountCounter;
import com.epam.university.spring.core.domain.User;
import com.epam.university.spring.core.service.DiscountStrategy;
import com.epam.university.spring.core.service.DiscountStrategyType;

import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 09.11.2015.
 */
public class DiscountCounterDaoImpl extends GenericDaoImpl<User, DiscountCounter> implements DiscountCounterDao {
    public DiscountCounterDaoImpl(Map<User, DiscountCounter> storage) {
        super(storage);
    }

    public void increment(User user, DiscountStrategyType discountStrategyType) {
        DiscountCounter discountCounter = get(user);
        if (discountCounter == null) {
            discountCounter = new DiscountCounter();
            discountCounter.setId(user);
        }
        discountCounter.increment(discountStrategyType);
        update(discountCounter);
    }
}
