package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.InMemoryStorage.UserDaoImpl;
import com.epam.university.spring.core.domain.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.sql.Date;
import java.util.UUID;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-28
 * Time: 11:27
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class BookingServiceTest extends TestCase {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;


    private User dummyRegisteredUser;
    private User dummyRegisteredUserWithBookedTickets;
    private User dummyUnregisteredUser;
    private Event dummyEventWithHighRating;
    private Event dummyEventWithMidRating;
    private Event dummyEventWithLowRating;
    private int vipSeat;
    private int noVipSeat;
    private Date dummyDateOfEventWithHighRating;
    private Date dummyDateOfEventWithMidRating;
    private Date dummyDateOfEventWithLowRating;

    @Before
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        dummyRegisteredUser = userService.register("John Doe", "j.doe@example.com", new Date(calendar.getTimeInMillis()));
        calendar.set(1980, Calendar.MAY, 1);
        dummyUnregisteredUser = new User("James Smith", "j.smith@example.com", new Date(calendar.getTimeInMillis()));
        dummyUnregisteredUser.setId(UUID.randomUUID().getMostSignificantBits());

        dummyEventWithHighRating = eventService.create("Star Wars: Episode VII - The Force Awakens", 100, EventRating.HIGH);
        dummyEventWithMidRating = eventService.create("The Martian", 100, EventRating.MID);
        dummyEventWithLowRating = eventService.create("The some movie", 100, EventRating.LOW);

        Auditorium auditorium = auditoriumService.getAuditoriumById(1L);
        vipSeat = 1;
        noVipSeat = 10;

        calendar.set(2016, Calendar.JANUARY, 1);
        dummyDateOfEventWithHighRating = new Date(calendar.getTimeInMillis());
        eventService.assignAuditorium(dummyEventWithHighRating, auditorium, dummyDateOfEventWithHighRating);
        calendar.set(2016, Calendar.FEBRUARY, 2);
        dummyDateOfEventWithMidRating = new Date(calendar.getTimeInMillis());
        eventService.assignAuditorium(dummyEventWithMidRating, auditorium, dummyDateOfEventWithMidRating);
        calendar.set(2016, Calendar.MARCH, 3);
        dummyDateOfEventWithLowRating = new Date(calendar.getTimeInMillis());
        eventService.assignAuditorium(dummyEventWithLowRating, auditorium, dummyDateOfEventWithLowRating);

        dummyRegisteredUserWithBookedTickets = userService.register("George Brown", "g.brown@example.com", new Date(calendar.getTimeInMillis()));
        for (int i=0; i<9; i++) {
            bookingService.bookTicket(dummyEventWithLowRating, dummyDateOfEventWithLowRating, 20 + i, dummyRegisteredUserWithBookedTickets);
        }

    }

    @After
    public void afterTest() {
        userDao.delete();
    }

    @Test
    public void testInjectBookingService() {
        assertNotNull(bookingService);
    }

    //@Ignore
    @Test
    public void testGetTicketPrice() {
        //  HIGH Rating +   No VIP  +   No Discount
        assertEquals(120.00, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, noVipSeat, dummyUnregisteredUser));
        //  HIGH Rating +   No VIP  +   Birthday discount
        //dummyRegisteredUser.setBirthday(new Date());
        assertEquals(114.00, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, noVipSeat, dummyRegisteredUser));
        //  HIGH Rating +   No VIP  +   10th ticket discount
        assertEquals(60.00, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, noVipSeat, dummyRegisteredUserWithBookedTickets));
        //  HIGH Rating +   VIP     +   No Discount
        assertEquals(144.00, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, vipSeat, dummyUnregisteredUser));
        //  HIGH Rating +   VIP     +   Birthday discount
        assertEquals(136.80, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, vipSeat, dummyRegisteredUser));
        //  HIGH Rating +   VIP     +   10th ticket discount
        assertEquals(72.00, bookingService.getTicketPrice(dummyEventWithHighRating, dummyDateOfEventWithHighRating, vipSeat, dummyRegisteredUserWithBookedTickets));
        //  MID Rating  +   No VIP  +   No Discount
        assertEquals(100.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, noVipSeat, dummyUnregisteredUser));
        //  MID Rating  +   No VIP  +   Birthday discount
        assertEquals(95.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, noVipSeat, dummyRegisteredUser));
        //  MID Rating  +   No VIP  +   10th ticket discount
        assertEquals(50.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, noVipSeat, dummyRegisteredUserWithBookedTickets));
        //  MID Rating  +   VIP     +   No Discount
        assertEquals(120.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, vipSeat, dummyUnregisteredUser));
        //  MID Rating  +   VIP     +   Birthday discount
        assertEquals(114.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, vipSeat, dummyRegisteredUser));
        //  MID Rating  +   VIP     +   10th ticket discount
        assertEquals(60.00, bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, vipSeat, dummyRegisteredUserWithBookedTickets));
        //  LOW Rating  +   No VIP  +   No Discount
        assertEquals(80.00, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, noVipSeat, dummyUnregisteredUser));
        //  LOW Rating  +   No VIP  +   Birthday discount
        assertEquals(76.00, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, noVipSeat, dummyRegisteredUser));
        //  LOW Rating  +   No VIP  +   10th ticket discount
        assertEquals(40.00, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, noVipSeat, dummyRegisteredUserWithBookedTickets));
        //  LOW Rating  +   VIP     +   No Discount
        assertEquals(96.00, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, vipSeat, dummyUnregisteredUser));
        //  LOW Rating  +   VIP     +   Birthday discount
        assertEquals(91.20, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, vipSeat, dummyRegisteredUser));
        //  LOW Rating  +   VIP     +   10th ticket discount
        assertEquals(48.00, bookingService.getTicketPrice(dummyEventWithLowRating, dummyDateOfEventWithLowRating, vipSeat, dummyRegisteredUserWithBookedTickets));
    }

    @Test
    public void testBookTicket() {
        final int SEAT = 15;
        Ticket ticket = bookingService.bookTicket(dummyEventWithMidRating, dummyDateOfEventWithMidRating, SEAT, dummyRegisteredUser);
        assertNotNull(ticket);
        assertEquals(dummyRegisteredUser, ticket.getUser());
        assertEquals(dummyEventWithMidRating, ticket.getEventShowing().getEvent());
        assertEquals(dummyDateOfEventWithMidRating, ticket.getEventShowing().getDate());
        assertEquals(SEAT, ticket.getSeat());
        assertEquals(bookingService.getTicketPrice(dummyEventWithMidRating, dummyDateOfEventWithMidRating, SEAT, dummyRegisteredUser), ticket.getCost());
    }

    @Test
    public void testGetTicketsForEvent() {
        assertEquals(9, bookingService.getTicketsForEvent(dummyEventWithLowRating, dummyDateOfEventWithLowRating).size());
    }
}
