package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class BookingService {

    private static final int VIP_MARKUP = 20;

    @Autowired
    @Qualifier("userDao")
    private GenericDao<Long, User> userDao;

    @Autowired
    @Qualifier("eventDao")
    private GenericDao<Long, Event> eventDao;

    @Autowired
    @Qualifier("auditoriumDao")
    private GenericDao<Long, Auditorium> auditoriumDao;

    @Autowired
    @Qualifier("eventShowingDao")
    private GenericDao<Long, EventShowing> eventShowingDao;

    @Autowired
    @Qualifier("ticketDao")
    private GenericDao<Long, Ticket> ticketDao;

    public double getTicketPrice(Event event, Date date, Integer seat, User user) {
        double price = event.getBasePrice();

        return price;
    }

    public Ticket bookTicket(Event event, Date date, Integer seat, User user) {
        return null;
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        return null;
    }
}
