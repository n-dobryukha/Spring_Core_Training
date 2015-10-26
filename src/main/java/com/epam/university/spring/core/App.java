package com.epam.university.spring.core;

import com.epam.university.spring.core.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class App {

    public static String USER_STORAGE = "USER";

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/app.xml");

        UserService userService = (UserService) ctx.getBean("userService");

        EventService eventService = (EventService) ctx.getBean("eventService");

        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumService");

        DiscountService discountService = (DiscountService) ctx.getBean("discountService");

        BookingService bookingService = (BookingService) ctx.getBean("bookingService");

    }
}
