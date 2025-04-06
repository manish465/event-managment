package com.manish.event.utils;

import org.springframework.stereotype.Component;

public class CompareStringUtils {
    public static boolean isStingEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
