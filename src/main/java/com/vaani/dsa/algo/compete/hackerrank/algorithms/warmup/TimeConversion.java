package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

public class TimeConversion {
    static String timeConversion1(String s) {
        /*
         * Write your code here.
         */
        String[] timeSplit = s.split(":");
        String hours = timeSplit[0];
        String minutes = timeSplit[1];
        String seconds = timeSplit[2].substring(0, 2);
        String ampm = timeSplit[2].substring(2);

        int hour = Integer.parseInt(hours);

        int differential = 0;
        if ("PM".equals(ampm) && hour != 12) {
            differential = 12;
        }


        hour += differential;
        hour = hour % 24;

        hours = String.format("%02d", hour);

        return hours + ":" + minutes + ":" + seconds;

    }

    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        String[] timeSplit = s.split(":");
        String hours = timeSplit[0];
        String minutes = timeSplit[1];
        String seconds = timeSplit[2].substring(0, 2);
        String ampm = timeSplit[2].substring(2, 4);

        String newHours;
        if (ampm.equals("AM")) {
            newHours = hours.equals("12") ? "00" : hours;

        } else {
            newHours = hours.equals("12") ? hours : String.valueOf(Integer.parseInt(hours) + 12);
        }

        return newHours + ":" + minutes + ":" + seconds;

    }
}
