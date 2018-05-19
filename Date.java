/*
 * Shinya Aoi
 * 05/17/2018
 * CSS143 HW interface
 */

import java.io.Serializable;

/**
 * This class is represent a date.
 * Day has to be between 1 - 31, month has to be between 1 - 12,
 * and year has to be between 2014 - 2024.
 * Comparable takes only Date class/ object.
 */
public class Date implements Comparable<Date>, Cloneable,Serializable {
    private int day;
    private int month;
    private int year;

    /**
     * Constructor that takes parameters of month, day and year.
     * And using the setDay method to check those values are
     * valid. If not, the program will stop.
     * @param month
     * @param day
     * @param year
     */
    public Date(int month, int day, int year){
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    /**
     * Clone method to copy an object.
     * @return
     */
    @Override
    public Date clone(){
        try{
            return (Date)(super.clone());
        }catch (CloneNotSupportedException e){
            throw new InternalError("Clone was not right!");
        }
    }

    /**
     * This method returns a value of year.
     * @return year
     */
    public int getYear(){
        return year;
    }

    /**
     * This method returns a value of month.
     * @return month
     */
    public int getMonth(){
        return month;
    }

    /**
     * This method returns a value of day.
     * @return day
     */
    public int getDay(){
        return day;
    }

    /**
     * This method sets a day. If a value is not valid,
     * throw it to the exception.
     * @param day
     */
    public void setDay(int day) {
        if(day<1 || day>31){
            throw new IllegalArgumentException("Day is not valid. Please put a day between 1-31!");
        }
        this.day = day;
    }

    /**
     * This method sets a month. If a value is not valid,
     * throw it to the exception.
     * @param month
     */
    public void setMonth(int month) {
        if(month<1||month>12){
            throw new IllegalArgumentException("Month is not valid. Please put a month between 1-12!");
        }
        this.month = month;
    }

    /**
     * This method sets a year. If a value is not valid,
     * throw it to the exception.
     * @param year
     */
    public void setYear(int year) {
        if(year<2014||year>2024){
            throw new IllegalArgumentException("Year is not valid. Please put a year between 2014-2024!");
        }
        this.year = year;
    }

    /**
     * This method checks if a date that has been passed as an argument
     * is later than the date is compared.
     * @param compareTo - an object
     * @return true, if it is after compareTo object.
     */
    public boolean isAfter(Date compareTo){
        if(this.year>compareTo.year) return false;
        else if(this.year==compareTo.year){
            if (this.month>compareTo.month) return false;
            else if(this.month==compareTo.month){
                if(this.day > compareTo.day) return false;
            }
        }
        return true;
    }

    /**
     * Override that compares if a date that has been passed as argument
     * is the same as the one is compared.
     * @param date - an object
     * @return true, if they are the same
     */
    @Override
    public boolean equals(Object date){
        if(!(date instanceof Date)) return false;
        Date that = (Date) date;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }

    /**
     * Override to toString method. If day or month are a single digit,
     * add 0 to it.
     * @return the string value of date.
     */
    @Override
    public String toString() {
        if(month<10&&day<10){
            return "0"+month+"/"+"0"+day+"/"+year;
        }
        else if(day<10 && month>=10){
            return month+"/"+"0"+day+"/"+year;
        }
        else if(month<10 && day>=10){
            return "0"+month+"/"+day+"/"+year;
        }
        return month+"/"+day+"/"+year;
    }

    /**
     * Override the compareTo method to compare the object.
     * @param obj
     * @return negative if thisDays is before compareDay.
     * if they are equal, return 0. Otherwise, return positive number.
     */
    @Override
    public int compareTo(Date obj) {
        int thisDays = this.year * 372;
        thisDays = thisDays + this.month * 31;
        thisDays = thisDays + this.day;

        int compareDays = obj.year * 372;
        compareDays = compareDays + obj.month * 31;
        compareDays = compareDays + obj.day;

        return thisDays-compareDays;
    }
}
