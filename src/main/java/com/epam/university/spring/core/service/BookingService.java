package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.*;
import com.epam.university.spring.core.domain.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class BookingService implements ApplicationContextAware {

    private ApplicationContext appContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    private static final int VIP_MARKUP = 20;
    private static final int HIGH_RATING_MARKUP = 20;
    private static final int LOW_RATING_MARKUP = -20;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("eventDao")
    private EventDao eventDao;

    @Autowired
    @Qualifier("auditoriumDao")
    private AuditoriumDao auditoriumDao;

    @Autowired
    @Qualifier("eventShowingDao")
    private EventShowingDao eventShowingDao;

    @Autowired
    @Qualifier("ticketDao")
    private TicketDao ticketDao;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Autowired
    @Qualifier("discountService")
    private DiscountService discountService;

    public double getTicketPrice(Event event, Date date, Integer seat, User user) {
        double price = event.getBasePrice();
        int ratingMarkup = 0;
        switch (event.getRating()) {
            case HIGH:
                ratingMarkup = HIGH_RATING_MARKUP;
                break;
            case LOW:
                ratingMarkup = LOW_RATING_MARKUP;
                break;
            default:
                break;
        }
        EventShowing eventShowing = eventService.getEventShowing(event, date);
        int vipMarkup = 0;
        if (eventShowing != null) {
            Auditorium auditorium = eventShowing.getAuditorium();
            if (auditorium.isVIP(seat)) vipMarkup = VIP_MARKUP;
        }
        int discount = discountService.getDiscount(user, event, date);
        return price * (1 + ratingMarkup/100) * (1 + vipMarkup/100) * (1 - discount/100);
    }

    public Ticket bookTicket(Event event, Date date, Integer seat, User user) {
        Ticket ticket = (Ticket) appContext.getBean("ticket");
        EventShowing eventShowing = eventService.getEventShowing(event, date);
        if (eventShowing == null) return null;
        ticket.setEventShowing(eventShowing);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setCost(getTicketPrice(event, date, seat, user));
        ticketDao.create(ticket);
        return ticket;
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        EventShowing eventShowing = eventService.getEventShowing(event, date);
        if (eventShowing == null) return null;
        return ticketDao.getTicketsByEventShowing(eventShowing);
    }
}
