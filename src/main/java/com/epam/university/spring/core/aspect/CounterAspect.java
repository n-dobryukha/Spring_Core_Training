package com.epam.university.spring.core.aspect;

import com.epam.university.spring.core.dao.EventCounterDao;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventCounter;
import org.aspectj.lang.annotation.After;
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
public class CounterAspect {

    @Autowired
    @Qualifier("eventCounterDao")
    private EventCounterDao eventCounterDao;

    @AfterReturning(pointcut = "execution(public * com.epam.university.spring.core.service.EventService.getEventByName(..))",
                    returning = "event")
    public void accessByName(Event event) {
        EventCounter eventCounter = eventCounterDao.get(event);
        if (null == eventCounter) {
            eventCounter = new EventCounter();
            eventCounter.setId(event);
        }
        eventCounter.incCounterAccessByName();
        eventCounterDao.update(eventCounter);
    }

    @After("execution(public * com.epam.university.spring.core.service.BookingService.getTicketPrice(..)) && args(event,..)")
    public void getTicketPrice(Event event) {
        EventCounter eventCounter = eventCounterDao.get(event);
        if (null == eventCounter) {
            eventCounter = new EventCounter();
            eventCounter.setId(event);
        }
        eventCounter.incCounterPriceQueried();
        eventCounterDao.update(eventCounter);
    }

    @After("execution(public * com.epam.university.spring.core.service.BookingService.bookTicket(..)) && args(event,..)")
    public void bookTicket(Event event) {
        EventCounter eventCounter = eventCounterDao.get(event);
        if (null == eventCounter) {
            eventCounter = new EventCounter();
            eventCounter.setId(event);
        }
        eventCounter.incCounterTicketsBooked();
        eventCounterDao.update(eventCounter);
    }

}
