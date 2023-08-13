package com.example.birthdaycalculator;

import java.time.LocalDate;

public class BDayCalc {


    /*Fields
     * u before the name means how many months/days/years old the person is */
    private int month, day, year, uMonth, uDay, uYear;


    /*constructors*/
    public BDayCalc() {
        this.month = 0;
        this.day = 0;
        this.year = 0;
        this.uDay = 0;
        this.uMonth = 0;
        this.uYear = 0;
    }

    public BDayCalc(int month, int day, int year, int uDay, int uMonth, int uYear) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.uDay = uDay;
        this.uMonth = uMonth;
        this.uYear = uYear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = checkAndGetGreaterThanZero(month, "Month");
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = checkAndGetGreaterThanZero(day, "Day");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = checkAndGetGreaterThanZero(year, "Year");
    }

    public int getUserMonth() {
        return uMonth;
    }
    public void setUserMonth(int uMonth) {
        this.uMonth = checkAndGetGreaterThanZero(uMonth, "Month");
    }
    public int getUserDay() { return uDay; }
    public void setUserDay(int uDay) {
        this.uDay = checkAndGetGreaterThanZero(uDay, "Day");
    }
    public int getUserYear() {return uYear;}
    public void setUserYear(int uYear) {this.uYear = checkAndGetGreaterThanZero(uYear, "Year");}
    private int checkAndGetGreaterThanZero(int value, String description) {
        if (value > 0)
            return value;
        else
            throw new IllegalArgumentException(description + " must be greater than zero.");
    }

    // Calculates how old the user is based on the date now
    public void calcDates() {
        LocalDate date = LocalDate.now();
        int dayNow = date.getDayOfMonth();
        int monthNow = date.getMonthValue();
        int yearNow = date.getYear();
        /* Checks that the result won't be negative, like for example if the user was born
         * in January, and it's now August, the result would be -7 because 1-8=-7 */
        if (dayNow >= this.day) {
            amtDay(dayNow);
        } else {
            dayNow += 30;
            monthNow--;
            setUserDay(amtDay(dayNow));
        }
        if (monthNow >= this.month) {
            amtMonth(monthNow);
        } else {
            monthNow += 12;
            yearNow--;
            amtMonth(monthNow);
        }
        setUserMonth(amtMonth(monthNow));
        amtYear(yearNow);
        setUserYear(amtYear(yearNow));
    }

    /*nMonth==now month
     * uMonth - month the user was born in
     * method returns the months/days/years old user is */
    public int amtMonth(int nMonth) {
        return nMonth - this.month;
    }

    public int amtYear(int nYear) {
        return nYear - this.year;
    }

    public int amtDay(int nDay) {
        return nDay - this.day;
    }
}
