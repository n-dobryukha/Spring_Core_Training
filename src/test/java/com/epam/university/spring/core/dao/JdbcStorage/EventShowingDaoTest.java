package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import com.epam.university.spring.core.domain.EventShowing;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 16.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test.xml"})
public class EventShowingDaoTest extends TestCase {
    @Autowired
    private EventShowingDaoImpl eventShowingDao;

    @Autowired
    private EventDaoImpl eventDao;

    @Autowired
    private AuditoriumDaoImpl auditoriumDao;

    private EventShowing dummyEventShowing;

    @Test
    public void testInjectEventShowingDao() {
        assertNotNull(eventShowingDao);
    }

    @Before
    public void setUp() {
        Event dummyEvent = new Event("Star Wars: Episode VII - The Force Awakens", 100.00, EventRating.HIGH);
        eventDao.create(dummyEvent);

        Auditorium dummyAuditorium = auditoriumDao.get(1L);

        Date dummyDate = new Date(115, 1, 1);

        dummyEventShowing = new EventShowing(dummyEvent, dummyAuditorium, dummyDate);
    }

    @After
    public void tearDown() throws Exception {
        eventShowingDao.delete();
        dummyEventShowing = null;
    }

    private Long createDummyEventShowing() {
        Long id = eventShowingDao.create(dummyEventShowing);
        assertNotNull(id);
        assertNotNull(dummyEventShowing.getId());
        assertEquals(id, dummyEventShowing.getId());
        return id;
    }

    @Test
    public void testCreate() {
        assertNull(dummyEventShowing.getId());
        createDummyEventShowing();
    }

    @Test
    public void testGetById() {
        Long id = createDummyEventShowing();
        EventShowing dummyEventShowingReceived = eventShowingDao.get(id);
        assertEquals(dummyEventShowing, dummyEventShowingReceived);
    }

    @Test
    public void testGetAll() {
        createDummyEventShowing();
        List<EventShowing> dummyEventShowings = (List<EventShowing>) eventShowingDao.get();
        assertEquals(1, dummyEventShowings.size());
    }

    @Test
    public void testUpdate() {
        createDummyEventShowing();
        eventShowingDao.update(dummyEventShowing);
        EventShowing dummyEventShowingReceived = eventShowingDao.get(dummyEventShowing.getId());
        assertEquals(dummyEventShowing, dummyEventShowingReceived);
    }

    @Test
    public void testDelete() {
        createDummyEventShowing();
        List<EventShowing> dummyEventShowings = (List<EventShowing>) eventShowingDao.get();
        assertEquals(1, dummyEventShowings.size());
        eventShowingDao.delete(dummyEventShowing);
        dummyEventShowings = (List<EventShowing>) eventShowingDao.get();
        assertEquals(0, dummyEventShowings.size());
    }

    @Test
    public void testDeleteAll() {
        createDummyEventShowing();
        List<EventShowing> dummyEventShowings = (List<EventShowing>) eventShowingDao.get();
        assertEquals(1, dummyEventShowings.size());
        eventShowingDao.delete();
        dummyEventShowings = (List<EventShowing>) eventShowingDao.get();
        assertEquals(0, dummyEventShowings.size());
    }
}
