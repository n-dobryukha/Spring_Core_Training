package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.dao.TicketDao;
import com.epam.university.spring.core.domain.EventShowing;
import com.epam.university.spring.core.domain.Ticket;
import com.epam.university.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 11:01
 */
public class TicketDaoImpl extends GenericDaoImpl<Long, Ticket> implements TicketDao {

    public TicketDaoImpl(@Qualifier("ticketStorage") Map<Long, Ticket> storage) {
        super(storage);
    }

    @Override
    public Long create(Ticket object) {
        object.setId(UUID.randomUUID().getMostSignificantBits());
        return super.create(object);
    }

    public List<Ticket> getTicketsByEventShowing(EventShowing eventShowing) {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket: get()) {
            if (eventShowing.equals(ticket.getEventShowing())) tickets.add(ticket);
        }
        return tickets;
    }

    public List<Ticket> getBookedTickets(User user) {
        List<Ticket> bookedTickets = new ArrayList<>();
        for (Ticket ticket: get()) {
            if (user.equals(ticket.getUser())) bookedTickets.add(ticket);
        }
        return bookedTickets;
    }
}
