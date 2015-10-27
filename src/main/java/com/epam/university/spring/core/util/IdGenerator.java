package com.epam.university.spring.core.util;

import java.util.UUID;

/**
 * Created by Nikita Dobriukha
 * Date: 28.10.2015.
 */
public class IdGenerator {

    public static Long getLongId() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}
