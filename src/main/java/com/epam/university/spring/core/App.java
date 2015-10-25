package com.epam.university.spring.core;

import com.epam.university.spring.core.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikita Dobriukha
 * Date: 25.10.2015.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring.xml");

        Map<String, User> storage = (HashMap) ctx.getBean("userStorage");

        User user = new User();

        storage.put("1", user);

        System.out.println(storage.size());
    }
}
