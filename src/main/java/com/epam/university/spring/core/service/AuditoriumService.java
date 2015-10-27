package com.epam.university.spring.core.service;

import com.epam.university.spring.core.dao.AuditoriumDao;
import com.epam.university.spring.core.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class AuditoriumService {

    @Autowired
    @Qualifier("auditoriumDao")
    private AuditoriumDao auditoriumDao;

    public List<Auditorium> getAll() {
        return new ArrayList<Auditorium>(auditoriumDao.get());
    }

}
