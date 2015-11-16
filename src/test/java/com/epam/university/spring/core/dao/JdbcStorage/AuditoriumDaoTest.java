package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.AuditoriumDao;
import com.epam.university.spring.core.domain.Auditorium;
import com.epam.university.spring.core.domain.Event;
import com.epam.university.spring.core.domain.EventRating;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 15.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test.xml"})
public class AuditoriumDaoTest extends TestCase {
    @Autowired
    private AuditoriumDaoImpl auditoriumDao;

    private Auditorium dummyAuditorium;

    @Before
    public void setUp() {
        dummyAuditorium = new Auditorium();
        dummyAuditorium.setName("RED");
        dummyAuditorium.setNumberOfSeats(50);
        dummyAuditorium.setVipSeats(new ArrayList<Integer>() {{ add(1); add(2); add(3); add(4); add(5); }} );
    }

    @After
    public void tearDown() throws Exception {
        auditoriumDao.delete(dummyAuditorium);
        dummyAuditorium = null;
    }

    @Test
    public void testInjectAuditoriumDao() {
        assertNotNull(auditoriumDao);
    }

    private Long createDummyAuditorium() {
        Long id = auditoriumDao.create(dummyAuditorium);
        assertNotNull(id);
        assertNotNull(dummyAuditorium.getId());
        assertEquals(id, dummyAuditorium.getId());
        return id;
    }

    @Test
    public void testCreate() {
        assertNull(dummyAuditorium.getId());
        createDummyAuditorium();
    }

    @Test
    public void testGetById() {
        Long id = createDummyAuditorium();
        Auditorium dummyAuditoriumReceived = auditoriumDao.get(id);
        assertEquals(dummyAuditorium, dummyAuditoriumReceived);
    }

    @Test
    public void testGetAll() {
        List<Auditorium> dummyAuditoriums = (List<Auditorium>) auditoriumDao.get();
        assertEquals(2, dummyAuditoriums.size());
    }

    @Test
    public void testUpdate() {
        createDummyAuditorium();
        dummyAuditorium.setName("BLUE");
        dummyAuditorium.setNumberOfSeats(100);
        auditoriumDao.update(dummyAuditorium);
        Auditorium dummyAuditoriumReceived = auditoriumDao.get(dummyAuditorium.getId());
        assertEquals(dummyAuditorium, dummyAuditoriumReceived);
    }

    @Test
    public void testDelete() {
        createDummyAuditorium();
        List<Auditorium> dummyAuditoriums = (List<Auditorium>) auditoriumDao.get();
        assertEquals(3, dummyAuditoriums.size());
        auditoriumDao.delete(dummyAuditorium);
        dummyAuditoriums = (List<Auditorium>) auditoriumDao.get();
        assertEquals(2, dummyAuditoriums.size());
    }

    @Ignore
    @Test
    public void testDeleteAll() {
        createDummyAuditorium();
        List<Auditorium> dummyAuditoriums = (List<Auditorium>) auditoriumDao.get();
        assertEquals(1, dummyAuditoriums.size());
        auditoriumDao.delete();
        dummyAuditoriums = (List<Auditorium>) auditoriumDao.get();
        assertEquals(0, dummyAuditoriums.size());
    }
}
