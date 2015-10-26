package com.epam.university.spring.core.dao.InMemoryStorage;

import com.epam.university.spring.core.domain.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 11:01
 */
public class TicketDao extends GenericDaoImpl<Long, Ticket> {

    public TicketDao(@Qualifier("ticketStorage") Map<Long, Ticket> storage) {
        super(storage);
    }
}
