package com.transportsystem.backend.generators;

import java.util.Random;

/**
 * Created by DZCKJS0 on 06.12.2019.
 */
public class TransportOrderGenerator {
    public static String generator() {
        int length = 16;
        Random r = new Random();
        String s = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return s;
    }

}
