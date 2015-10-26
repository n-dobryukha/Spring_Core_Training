package com.epam.university.spring.core;

import com.epam.university.spring.core.service.DiscountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class App {

    public static String USER_STORAGE = "USER";

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/beans.xml");

        /*UserService userService = (UserService) ctx.getBean("userService");

        User user = userService.register("John Doe", "mail@example.com");

        System.out.println(user.getId());*/

        /*AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumService");

        System.out.println(auditoriumService.getAll().size());*/

        DiscountService auditoriumService = (DiscountService) ctx.getBean("discountService");

    }
}
