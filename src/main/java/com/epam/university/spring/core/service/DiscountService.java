package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 10:38
 */
public class DiscountService {

    @Autowired
    private UserService userService;

    @Resource
    private List<DiscountStrategy> discountStrategies;

    public int getDiscount(User user, Event event, Date date) {
        int maxDiscount = 0;
        for (DiscountStrategy strategy: discountStrategies) {
            switch (strategy.getDiscountType()) {
                case BIRTHDAY:
                    if (user.isBirthday()) {
                        if (maxDiscount < strategy.getDiscountValue() ) {
                            maxDiscount = strategy.getDiscountValue();
                        }
                    }
                    break;
                case EVERY_10TH:
                    if (userService.getBookedTickets(user).size()%10 == 0) {
                        if (maxDiscount < strategy.getDiscountValue() ) {
                            maxDiscount = strategy.getDiscountValue();
                        }
                    }
                    break;
                default: break;
            }
        }
        return maxDiscount;
    }
}
