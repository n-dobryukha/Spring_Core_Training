package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Auditorium;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-28
 * Time: 11:20
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app.xml"})
public class AuditoriumServiceTest extends TestCase {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void testInjectAuditoriumService() {
        assertNotNull(auditoriumService);
    }

    @Test
    public void testGetAll() {
        List<Auditorium> auditoriums = auditoriumService.getAll();
        assertNotNull(auditoriums);
        assertEquals(2, auditoriums.size());
    }

}
