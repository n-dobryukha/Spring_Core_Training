package com.epam.university.spring.core.domain;

import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class User {
    private Long id;
    private String name;
    private String email;
    private List<Ticket> bookedTickest;
}
