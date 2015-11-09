package com.epam.university.spring.core.domain;

import com.epam.university.spring.core.dao.Storable;
import com.epam.university.spring.core.service.DiscountStrategyType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 09.11.2015.
 */
public class DiscountCounter implements Storable<User> {

    private User id;

    @Override
    public User getId() {
        return this.id;
    }

    @Override
    public void setId(User id) {
        this.id = id;
    }

    private Map<DiscountStrategyType, Long> counter = new HashMap<>();

    public Long getCountByStrategyType(DiscountStrategyType discountStrategyType) {
        return counter.get(discountStrategyType);
    }

    public void increment(DiscountStrategyType discountStrategyType) {
        if (!counter.containsKey(discountStrategyType)) {
            counter.put(discountStrategyType,0L);
        }
        counter.put(discountStrategyType, counter.get(discountStrategyType) + 1);
    }
}
