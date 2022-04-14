package com.example.goalsettingapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GoalDateHandler {

    public static String getCurrentDate() {
        Date today = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(today);
    }

    public static String getGoalDate(int position) {
        Date today = new Date();
        Date goalDate = new Date();

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        switch (position){
            case 0:
                int month = calendar.get(Calendar.MONTH)+1;
                int newMonth;
                if((month%3) != 0)
                    newMonth = month - ((month)%3) + 3;
                else
                    newMonth = month;


                calendar.set(Calendar.MONTH, newMonth-1);

                int newDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Makes day end of month

                calendar.set(Calendar.DAY_OF_MONTH, newDay);

                goalDate = calendar.getTime();
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.add(Calendar.YEAR, 1);
                goalDate = calendar.getTime();
                break;
            case 2:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.add(Calendar.YEAR, 5);
                goalDate = calendar.getTime();
                break;

        }
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(goalDate);
    }
}
