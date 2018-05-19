/*
 *Shinya Aoi
 * 05/17/2018
 * CSS143 InterfaceHW
 */

import java.io.Serializable;

/**
 * Money class is to track the amount of USD with using
 * two variables dollars and cents. Comparable takes only
 * Money object/class.
 */
public class Money implements Comparable<Money>, Cloneable, Serializable{
    private int dollars;
    private int cents;

    /**
     * Constructor with a parameter dol which is a dollar amount.
     * If dollar or cents is negative, it wll throw exception.
     * @param dol which is a value of dollar that has been passed.
     */
    public Money(int dol){
        setMoney(dol, 0);
    }

    /**
     * Constructor with two parameters which dollar and cent.
     * If dollar or cents is negative, it wll throw exception.
     * @param dol - dollar amount
     * @param cent - cent amount
     */
    public Money(int dol, int cent){
        setMoney(dol, cent);
    }

    /**
     * Clone method to copy an object.
     * @return
     */
    @Override
    public Money clone(){
        try{
            return (Money)(super.clone());
        }catch (CloneNotSupportedException e){
            throw new InternalError("Clone operation is not right!");
        }
    }

    /**
     * This method returns to the dollar amount.
     * @return dollars
     */
    public int getDollars(){
        return dollars;
    }

    /**
     * This method returns to the cent amount.
     * @return cents.
     */
    public int getCents(){
        return cents;
    }

    /**
     * This method sets the dollar and cent to those two
     * arguments that have been passed which are dollars
     * and cents. Throw Exception if arguments are negative amounts.
     * @param dollars
     * @param cents
     */
    public void setMoney(int dollars, int cents){
        if (dollars<0 || cents <0){
            throw new IllegalArgumentException("Dollars and cents cannot be negative!");
        }
        this.dollars = dollars+cents/100;
        this.cents = cents%100;
    }

    /**
     * This method returns the amount of Money.
     * @return the total amount of Money.
     */
    public double getMoney(){
        double changeCents = (double) cents/100;
        return (double) dollars+changeCents;
    }

    /**
     * This method adds a dollar amount. Throw exception if dollar is negative.
     * @param dol - dollar
     */
    public void add(int dol){
        if(dol<0){
            throw new IllegalArgumentException("Dollar has to be positive amount!");
        }
        dollars = dollars+dol;
    }

    /**
     * This methods adds both dollar and cent amounts.
     * If arguments are negative, trow them.
     * @param dol - dollar
     * @param cen - cent
     */
    public void add(int dol, int cen){
        if(dol<0 || cen<0){
            throw new IllegalArgumentException("Amounts have to be positive!");
        }
        dollars = dollars+dol;
        cents = cents+cen;
        dollars = dollars+cents/100;
        cents = cents%100;

    }

    /**
     * This methods adds a whole object's amount.
     * @param other
     */
    public void add(Money other){
        this.dollars = this.dollars+other.dollars;
        this.cents = this.cents+other.cents;
        int plusDol = this.cents/100;
        this.cents = this.cents%100;
        this.dollars = this.dollars+plusDol;
    }

    /**
     * Override for the equal method, and this checks if obj
     * that has been passed as an argument is the same it is
     * compared.
     * @param obj - an object that has been passed, it will change it to that.
     * @return true if they are equal.
     */
    @Override
    public boolean equals(Object obj){
        if( !(obj instanceof Money)) return false;
        Money that = (Money) obj;
        return this.dollars == that.dollars&& this.cents==that.cents;
    }

    /**
     * Override the toString method to print out dollar and cent as string.
     * @return the printed value of dollar and cent.
     */
    @Override
    public String toString() {
        if (cents<10){
            return "$"+dollars+"."+"0"+cents;
        }
        return "$"+dollars+"."+cents;
    }

    /**
     * Override the compareTo method to compare the object.
     * @param obj
     * @return negative if this is less than obj. Return positive
     * if this is bigger than obj. Return equal if they are the same.
     */
    @Override
    public int compareTo(Money obj) {
        int full = this.dollars*100 + this.cents;
        int compare = obj.dollars*100 + obj.cents;
        return full-compare;
        }
    }
