package com.example.birthdaycalculator;
import java.time.LocalDate;
public class BDayCalc {


        /*Fields*/
        private int month, day , year;


        /*constructors*/
        public BDayCalc(){
            this.month=0;
            this.day=0;
            this.year=0;}

        public BDayCalc(int month,int day, int year){
            this.month=month;
            this.day=day;
            this.year=year;
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
        private int checkAndGetGreaterThanZero (int value, String description)
        {
            if (value >0)
                return value;
            else
                throw new IllegalArgumentException (description + " must be greater than zero.");
        }
        /*nmonth==now month
         * lmonth==how many monthes old you are*/
        public int amtmonth() {
            LocalDate date = LocalDate.now();
            int nmonth = date.getMonthValue();
            int lmonth=nmonth-this.month;
            return lmonth;
        }
        public int amtyear() {
            LocalDate date = LocalDate.now();
            int nyear = date.getYear();
            int lyear=nyear-this.year;
            return lyear;
        }
        public int amtday() {
            LocalDate date = LocalDate.now();
            int nday = date.getDayOfMonth();
            int lday=nday-this.day;
            return lday;}

}
