package com.epam.university.spring.core;

import com.epam.university.spring.core.domain.User;
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

        /*EventService eventService = (EventService) ctx.getBean("eventService");

        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumService");

        DiscountService discountService = (DiscountService) ctx.getBean("discountService");

        BookingService bookingService = (BookingService) ctx.getBean("bookingService");*/

        User user1 = userService.register("John Doe", "j.doe@example.com");

        User user2 = userService.register("James Smith", "j.smith@example.com");

        System.out.printf("id = %d; name = %s; email = %s", user1.getId(), user1.getName(), user1.getEmail());
        System.out.printf("id = %d; name = %s; email = %s", user2.getId(), user2.getName(), user2.getEmail());

    }
}
