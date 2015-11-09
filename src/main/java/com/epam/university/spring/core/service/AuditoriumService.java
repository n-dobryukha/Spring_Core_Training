package com.epam.university.spring.core.service;

import com.epam.university.spring.core.domain.Auditorium;

import java.util.List;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 10:28
 */
public interface AuditoriumService {
    Auditorium getAuditoriumById(Long id);
    List<Auditorium> getAll();
}
