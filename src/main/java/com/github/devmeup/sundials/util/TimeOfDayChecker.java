package com.github.devmeup.sundials.util;

public abstract class TimeOfDayChecker {
    public static String getTimeOfDayString(long timeInTicks) {
        long ticks = timeInTicks % 24000;
        String timeOfDay = "";
        if (ticks >= 23000) {
            timeOfDay = "It is about sunrise.";
        } else if (ticks >= 0 && ticks < 6000) {
            timeOfDay = "It is morning.";
        } else if (ticks >= 6000 && ticks < 7000) {
            timeOfDay = "It is about noon.";
        } else if (ticks >= 7000 && ticks < 12000) {
            timeOfDay = "It is afternoon.";
        } else if (ticks >= 12000 && ticks < 13000) {
            timeOfDay = "It is about sunset.";
        } else if (ticks >= 13000) {
            timeOfDay = "It is nighttime.";
        }
        return timeOfDay;
    }

}
