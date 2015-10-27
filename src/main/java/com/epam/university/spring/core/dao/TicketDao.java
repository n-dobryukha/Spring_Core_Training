package com.epam.university.spring.core.dao;

import com.epam.university.spring.core.domain.EventShowing;
import com.epam.university.spring.core.domain.Ticket;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 28.10.2015.
 */
public interface TicketDao extends GenericDao<Long, Ticket> {

    List<Ticket> getTicketsByEventShowing(EventShowing eventShowing);

}
