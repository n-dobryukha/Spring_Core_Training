package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.InMemoryStorage.EventDaoImpl;
import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.EventShowing;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-28
 * Time: 10:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class EventServiceTest extends TestCase {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDaoImpl eventDao;

    @Autowired
    private AuditoriumService auditoriumService;

    private final double DUMMY_EVENT_PRICE = 500;
    private final EventRating DUMMY_EVENT_RATING_HIGH = EventRating.HIGH;
    private final EventRating DUMMY_EVENT_RATING_MID = EventRating.MID;
    private final EventRating DUMMY_EVENT_RATING_LOW = EventRating.LOW;

    @After
    public void afterTest() {
        eventDao.delete();
    }

    @Test
    public void testInjectEventService() {
        assertNotNull(eventService);
    }

    private Event createEvent() {
        String DUMMY_EVENT_NAME_1 = "The Martian";
        Event event = eventService.create(DUMMY_EVENT_NAME_1, DUMMY_EVENT_PRICE, DUMMY_EVENT_RATING_HIGH);
        assertNotNull(event);
        assertEquals(event, eventService.getEventById(event.getId()));
        return event;
    }

    @Test
    public void testCreate() {
        createEvent();
    }

    @Test
    public void testRemove() {
        Event event = createEvent();
        eventService.remove(event);
        assertNull(eventService.getEventById(event.getId()));
    }

    @Test
    public void testGetEventByName() {
        Event event = createEvent();
        assertEquals(event, eventService.getEventByName(event.getName()));
    }

    @Test
    public void testGetAll() {
        createEvent();
        String DUMMY_EVENT_NAME_2 = "Star Wars: Episode VII - The Force Awakens";
        eventService.create(DUMMY_EVENT_NAME_2, DUMMY_EVENT_PRICE, DUMMY_EVENT_RATING_MID);
        assertEquals(2, eventService.getAll().size());
    }

    private EventShowing createEventShowing() {
        Event event = createEvent();
        Auditorium auditorium = auditoriumService.getAll().get(0);
        assertNotNull(auditorium);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JANUARY, 1);
        EventShowing eventShowing = eventService.assignAuditorium(event, auditorium, calendar.getTime());
        assertNotNull(eventShowing);
        return eventShowing;
    }

    @Test
    public void testAssignAuditorium() {
        EventShowing eventShowing = createEventShowing();
        assertEquals(eventShowing, getEventShowing(eventShowing.getEvent(), eventShowing.getDate()));
    }

    private EventShowing getEventShowing(Event event, Date date) {
        EventShowing eventShowing = eventService.getEventShowing(event, date);
        assertNotNull(eventShowing);
        return eventShowing;
    }

    @Test
    public void testGetEventShowing() {
        EventShowing eventShowing = createEventShowing();
        assertEquals(eventShowing, eventService.getEventShowing(eventShowing.getEvent(), eventShowing.getDate()));
    }
}
