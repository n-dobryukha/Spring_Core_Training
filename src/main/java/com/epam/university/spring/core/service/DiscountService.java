package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.User;

import java.util.Date;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 10:32
 */
public interface DiscountService {
    DiscountStrategy getDiscountStrategy(User user, Event event, Date date);
}
