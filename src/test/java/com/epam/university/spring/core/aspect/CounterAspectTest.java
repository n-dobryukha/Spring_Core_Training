package com.epam.university.spring.core.aspect;

import com.epam.university.spring.core.dao.EventCounterDao;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.User;
import com.epam.university.spring.core.service.BookingService;
import com.epam.university.spring.core.service.EventService;
import com.epam.university.spring.core.service.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Calendar;

/**
 * Created by Nikita Dobriukha
 * Date: 08.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class CounterAspectTest extends TestCase{

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventCounterDao eventCounterDao;

    private final String DUMMY_EVENT_NAME = "Star Wars: Episode VII - The Force Awakens";
    private Event dummyEvent;
    private Date dummyDate;
    private User dummyUser;

    @Before
    public void setUp() {
        dummyEvent = eventService.create(DUMMY_EVENT_NAME, 100, EventRating.HIGH);
        dummyDate = Calendar.getInstance().getTime();
        dummyUser = userService.register("John Doe", "j.doe@example.com", dummyDate);
    }

    @Test
    public void testAccessByName() {
        for (int i=0; i<10; i++) {
            eventService.getEventByName(DUMMY_EVENT_NAME);
        }
        assertEquals(10, eventCounterDao.get(dummyEvent).getCountAccessByName());
    }

    @Test
    public void testGetTicketPrice() {
        for (int i=0; i<10; i++) {
            bookingService.getTicketPrice(dummyEvent, dummyDate, 10, dummyUser);
        }
        assertEquals(10, eventCounterDao.get(dummyEvent).getCountPriceQueried());
    }

    @Test
    public void testBookTicket() {
        for (int i=0; i<10; i++) {
            bookingService.bookTicket(dummyEvent, dummyDate, 10+i, dummyUser);
        }
        assertEquals(10, eventCounterDao.get(dummyEvent).getCountTicketsBooked());
    }
}
