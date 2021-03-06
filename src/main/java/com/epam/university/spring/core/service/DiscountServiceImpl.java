package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 10:38
 */
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private UserServiceImpl userService;

    @Resource
    private List<DiscountStrategy> discountStrategies;

    public DiscountStrategy getDiscountStrategy(User user, Event event, Date date) {
        Collections.sort(discountStrategies);
        for (DiscountStrategy strategy: discountStrategies) {
            switch (strategy.getDiscountType()) {
                case BIRTHDAY:
                    if (user.isBirthdayToday()) {
                        return strategy;
                    }
                    break;
                case EVERY_10TH:
                    int bookedTicketsCount = userService.getBookedTickets(user).size();
                    if ((bookedTicketsCount>0) && ((bookedTicketsCount+1)%10 == 0)) {
                        return strategy;
                    }
                    break;
                default: break;
            }
        }
        return null;
    }
}
