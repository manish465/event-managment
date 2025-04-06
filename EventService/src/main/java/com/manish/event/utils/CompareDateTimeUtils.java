package com.manish.event.utils;

import java.time.LocalDateTime;

public class CompareDateTimeUtils {
    public static boolean isDateTimeBefore(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isBefore(dateTime2);
    }

    public static boolean isDateTimeAfter(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isAfter(dateTime2);
    }

    public static boolean isDateTimeEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isEqual(dateTime2);
    }

    public static boolean isDateTimeEmpty(LocalDateTime dateTime) {
        return dateTime == null;
    }
}
