package com.epam.university.spring.core.dao.JdbcStorage;

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
 * Time: 17:10
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test.xml"})
public class GenericDaoTest extends TestCase {
    @Autowired
    private DummyObjectDao dummyDao;

    private DummyObject dummyObject = null;

    @Before
    public void setUp() {
        dummyObject = new DummyObject();
        dummyObject.setName("test name");
    }

    @After
    public void tearDown() throws Exception {
        dummyDao.delete();
        dummyObject = null;
    }

    @Test
    public void testInjectUserService() {
        assertNotNull(dummyDao);
    }

    private Long createDummyObject() {
        Long id = dummyDao.create(dummyObject);
        assertNotNull(id);
        assertNotNull(dummyObject.getId());
        assertEquals(id, dummyObject.getId());
        return id;
    }

    @Test
    public void testCreate() {
        assertNull(dummyObject.getId());
        createDummyObject();
    }

    @Test
    public void testGetById() {
        Long id = createDummyObject();
        DummyObject dummyObjectReceived = dummyDao.get(id);
        assertEquals(dummyObject, dummyObjectReceived);
    }

    @Test
    public void testGetAll() {
        createDummyObject();
        List<DummyObject> dummyObjects = (List<DummyObject>) dummyDao.get();
        assertEquals(1, dummyObjects.size());
    }

    @Test
    public void testUpdate() {
        createDummyObject();
        dummyObject.setName("changed name");
        dummyDao.update(dummyObject);
        DummyObject dummyObjectReceived = dummyDao.get(dummyObject.getId());
        assertEquals(dummyObject, dummyObjectReceived);
    }

    @Test
    public void testDelete() {
        createDummyObject();
        List<DummyObject> dummyObjects = (List<DummyObject>) dummyDao.get();
        assertEquals(1, dummyObjects.size());
        dummyDao.delete(dummyObject);
        dummyObjects = (List<DummyObject>) dummyDao.get();
        assertEquals(0, dummyObjects.size());
    }

    @Test
    public void testDeleteAll() {
        createDummyObject();
        List<DummyObject> dummyObjects = (List<DummyObject>) dummyDao.get();
        assertEquals(1, dummyObjects.size());
        dummyDao.delete();
        dummyObjects = (List<DummyObject>) dummyDao.get();
        assertEquals(0, dummyObjects.size());
    }


}
