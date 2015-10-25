package com.epam.university.spring.core.domain;

import java.util.Date;
import java.util.Currency;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class Event {
    private Long id;
    private String name;
    private Date date;
    private Currency basePrice;
    private EventRating rating;
}
