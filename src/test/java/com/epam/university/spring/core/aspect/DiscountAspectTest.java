package com.epam.university.spring.core.aspect;

import com.epam.university.spring.core.dao.InMemoryStorage.*;
import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.User;
import com.epam.university.spring.core.service.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 09:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class DiscountAspectTest extends TestCase {

    @Autowired
    @Qualifier("discountCounterDao")
    private DiscountCounterDaoImpl discountCounterDao;

    @Autowired
    @Qualifier("userDao")
    private UserDaoImpl userDao;

    @Autowired
    @Qualifier("eventDao")
    private EventDaoImpl eventDao;

    @Autowired
    @Qualifier("eventShowingDao")
    private EventShowingDaoImpl eventShowingDao;

    @Autowired
    @Qualifier("ticketDao")
    private TicketDaoImpl ticketDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @After
    public void clear() {
        userDao.delete();
        eventDao.delete();
        eventShowingDao.delete();
        ticketDao.delete();
    }

    @Test
    public void testGetDiscount() {
        discountCounterDao.delete();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.MAY, 1);
        User dummyUserOne = userService.register("John Doe", "j.doe@example.com", calendar.getTime());
        User dummyUserTwo = userService.register("James Smith", "j.smith@example.com", new Date());
        Event dummyEvent = eventService.create("The Martian", 100, EventRating.MID);
        Auditorium auditorium = auditoriumService.getAuditoriumById(1L);
        Date dummyEventDate = new Date();
        eventService.assignAuditorium(dummyEvent, auditorium, dummyEventDate);

        for (int i=0; i<10; i++) {
            bookingService.bookTicket(dummyEvent, dummyEventDate, 10 + i, dummyUserOne);
        }
        assertEquals(1, (long) discountCounterDao.get(null).getCountByStrategyType(DiscountStrategyType.EVERY_10TH));
        assertEquals(1, (long) discountCounterDao.get(dummyUserOne).getCountByStrategyType(DiscountStrategyType.EVERY_10TH));

        for (int i=0; i<9; i++) {
            bookingService.bookTicket(dummyEvent, dummyEventDate, 20 + i, dummyUserTwo);
        }
        assertEquals(9, (long) discountCounterDao.get(null).getCountByStrategyType(DiscountStrategyType.BIRTHDAY));
        assertEquals(9, (long) discountCounterDao.get(dummyUserTwo).getCountByStrategyType(DiscountStrategyType.BIRTHDAY));
        bookingService.bookTicket(dummyEvent, dummyEventDate, 30, dummyUserTwo);
        assertEquals(2, (long) discountCounterDao.get(null).getCountByStrategyType(DiscountStrategyType.EVERY_10TH));
        assertEquals(1, (long) discountCounterDao.get(dummyUserTwo).getCountByStrategyType(DiscountStrategyType.EVERY_10TH));
    }
}
