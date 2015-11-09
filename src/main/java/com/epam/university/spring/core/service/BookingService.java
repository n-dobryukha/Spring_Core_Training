package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;

import java.util.Date;
import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 10:27
 */
public interface BookingService {
    double getTicketPrice(Event event, Date date, Integer seat, User user);
    Ticket bookTicket(Event event, Date date, Integer seat, User user);
    List<Ticket> getTicketsForEvent(Event event, Date date);
}
