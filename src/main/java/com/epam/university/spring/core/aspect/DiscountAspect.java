package com.epam.university.spring.core.aspect;

import com.epam.university.spring.core.dao.DiscountCounterDao;
import com.epam.university.spring.core.domain.User;
import com.epam.university.spring.core.service.DiscountStrategy;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-06
 * Time: 15:37
 */
@Aspect
public class DiscountAspect {

    @Autowired
    @Qualifier("discountCounterDao")
    private DiscountCounterDao discountCounterDao;

    @AfterReturning(pointcut = "execution(public * com.epam.university.spring.core.service.DiscountServiceImpl.getDiscountStrategy(..)) && args(user, ..)",
            returning = "discountStrategy")
    public void getDiscount(User user, DiscountStrategy discountStrategy) {
        if (discountStrategy == null) return;
        discountCounterDao.increment(null, discountStrategy.getDiscountType());
        discountCounterDao.increment(user, discountStrategy.getDiscountType());
    }

}
