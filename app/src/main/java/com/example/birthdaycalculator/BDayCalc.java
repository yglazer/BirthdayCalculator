package com.example.birthdaycalculator;

import java.time.LocalDate;

public class BDayCalc {


    /*Fields
     * u before the name means how many months/days/years old the person is */
    private int month, day, year;
    private LocalDate date = LocalDate.now();
    private int dayNow = date.getDayOfMonth();
    private int monthNow = date.getMonthValue();
    private int yearNow = date.getYear();

    /*constructors*/
    public BDayCalc() {
        this.month = 0;
        this.day = 0;
        this.year = 0;
    }

    public BDayCalc(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = checkMonthIsValid(month);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = checkDayIsValid(day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = checkYearIsValid(year);
    }
    private int checkAndGetGreaterThanZero(int value, String description) {
        if (value > 0)
            return value;
        else
            throw new IllegalArgumentException(description + " must be greater than zero.");
    }

    // Checks that the day is only a number between 1 and 31
    private int checkDayIsValid(int day){
        if (day > 0 && day <= 31){
            return day;
        }
        else {
            throw new IllegalArgumentException("Day must be a number between 1 and 31");
        }
    }

    // Check that month is a valid month
    private int checkMonthIsValid(int month) {
        if (month > 0 && month <= 12) {
            return month;
        }
        else {
            throw new IllegalArgumentException("Month must be a number between 1 and 12");
        }
    }

    /* Checks that the year is not in the future because then the year will be negative */
    private int checkYearIsValid(int value) {
        LocalDate date = LocalDate.now();
        if (value <= date.getYear()){
            return value;
        }
        else {
            throw new IllegalArgumentException("Year must be less than year now");
        }
    }

    // Calculates how many days old the user is in addition to months and years
    public int calcDays(){
        int daysOld = 0;
        /* Checks that the result won't be negative, like for example if the user was born
         * on the 1st, and it's now the 17th, the result for day would be -16 because 1-17=-16 */
        if (dayNow >= this.day) {
            daysOld = amtDay(dayNow);
        } else {
            dayNow += 30;
            monthNow--;
            daysOld = amtDay(dayNow);
        }
        return daysOld;
    }

    public int calcMonths(){
        int monthsOld = 0;
        if (monthNow >= this.month) {
            monthsOld = amtMonth(monthNow);
        } else {
            monthNow += 12;
            yearNow--;
            monthsOld = amtMonth(monthNow);
        }
        return monthsOld;
    }

    public int calcYears(){
        return amtYear(yearNow);
    }

    /* nMonth==now month method returns the months/days/years old user is */
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
