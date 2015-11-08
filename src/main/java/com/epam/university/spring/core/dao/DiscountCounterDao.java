package com.epam.university.spring.core.dao;

import com.epam.university.spring.core.domain.DiscountCounter;
import com.epam.university.spring.core.domain.User;
import com.epam.university.spring.core.service.DiscountStrategyType;

/**
 * Created by Nikita Dobriukha
 * Date: 09.11.2015.
 */
public interface DiscountCounterDao extends GenericDao<User, DiscountCounter> {
    void increment(User user, DiscountStrategyType discountStrategyType);
}
