package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-10
 * Time: 18:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test.xml"})
public class EventDaoTest extends TestCase {
    @Autowired
    private EventDaoImpl eventDao;

    private Event dummyEvent;

    @Before
    public void setUp() {
        dummyEvent = new Event();
        dummyEvent.setName("Star Wars: Episode VII - The Force Awakens");
        dummyEvent.setBasePrice(100.00);
        dummyEvent.setRating(EventRating.HIGH);
    }

    @After
    public void tearDown() throws Exception {
        eventDao.delete();
        dummyEvent = null;
    }

    @Test
    public void testInjectEventDao() {
        assertNotNull(eventDao);
    }

    private Long createDummyEvent() {
        Long id = eventDao.create(dummyEvent);
        assertNotNull(id);
        assertNotNull(dummyEvent.getId());
        assertEquals(id, dummyEvent.getId());
        return id;
    }

    @Test
    public void testCreate() {
        assertNull(dummyEvent.getId());
        createDummyEvent();
    }

    @Test
    public void testGetById() {
        Long id = createDummyEvent();
        Event dummyEventReceived = eventDao.get(id);
        assertEquals(dummyEvent, dummyEventReceived);
    }

    @Test
    public void testGetAll() {
        createDummyEvent();
        List<Event> dummyEvents = (List<Event>) eventDao.get();
        assertEquals(1, dummyEvents.size());
    }

    @Test
    public void testUpdate() {
        createDummyEvent();
        dummyEvent.setName("The Martian");
        dummyEvent.setRating(EventRating.MID);
        eventDao.update(dummyEvent);
        Event dummyEventReceived = eventDao.get(dummyEvent.getId());
        assertEquals(dummyEvent, dummyEventReceived);
    }

    @Test
    public void testDelete() {
        createDummyEvent();
        List<Event> dummyEvents = (List<Event>) eventDao.get();
        assertEquals(1, dummyEvents.size());
        eventDao.delete(dummyEvent);
        dummyEvents = (List<Event>) eventDao.get();
        assertEquals(0, dummyEvents.size());
    }

    @Test
    public void testDeleteAll() {
        createDummyEvent();
        List<Event> dummyEvents = (List<Event>) eventDao.get();
        assertEquals(1, dummyEvents.size());
        eventDao.delete();
        dummyEvents = (List<Event>) eventDao.get();
        assertEquals(0, dummyEvents.size());
    }

    @Test
    public void testGetEventByName() {
        createDummyEvent();
        Event dummyEventReceived = eventDao.getEventByName(dummyEvent.getName());
        assertEquals(dummyEvent, dummyEventReceived);
    }
}
