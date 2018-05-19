/*
 * Shinya Aoi
 * 05/17/2018
 * CSS143 HW Interface
 */

import java.io.Serializable;

/**
 * This class represents a bill or outstanding. Each instance variables makes
 * a copy of it to avoid privacy leaks. String is immutable.
 * Comparable takes only Bill class/ object.
 */
public class Bill implements Comparable<Bill>, Cloneable, Serializable{
    private Money amount;
    private Date dueDate;
    private Date paidDate;
    private String originator;

    /**
     * Constructor that takes objects such as amount, dueDate, and originator.
     * Making a copy of data to prevent privacy leaks.
     * @param amount - an object from Money class.
     * @param dueDate - an object from Date
     * @param originator
     */
    public Bill(Money amount, Date dueDate, String originator){
        this.amount = amount.clone();//is this right?
        this.dueDate = dueDate.clone();
        this.paidDate = null;
        this.originator = originator;
    }

    @Override
    public Bill clone(){
        try {
            Bill bill = (Bill) super.clone();
            bill.amount = this.amount.clone();
            bill.dueDate = this.dueDate.clone();
            if (this.paidDate != null) {
                bill.paidDate = this.paidDate.clone();
            }
            return bill;
        }catch (CloneNotSupportedException e){
            throw new InternalError("Clone was not right!");
        }
    }

    /**
     * This returns a copy of amount of Money.
     * @return a copy of amount
     */
    public Money getAmount(){
        return amount.clone();
    }

    /**
     * This returns a copy of dueDate of Date.
     * @return a copy of dueDate
     */
    public Date getDueDate(){
        return dueDate.clone();
    }

    /**
     * This returns originator.
     * @return a string value of originator
     */
    public String getOriginator(){
        return originator;
    }

    /**
     * This method checks if the bill is paid.
     * @return true if it is paid which is not null.
     */
    public boolean isPaid(){
        return paidDate != null;
    }

    /**
     * This method sets a date when a bill is paid. If datePaid
     * is before the due date, update the paidDate and return tru.
     * Otherwise, return false.
     * @param datePaid
     * @return false if datePaid is after dueDate.
     */
    public boolean setPaid(Date datePaid){
        if(paidDate!=null||!dueDate.isAfter(datePaid)){
            return false;
        }
        paidDate = datePaid.clone();
        return true;
    }

    /**
     * This sets a due date. If the bill is already paid, this returns false.
     * If not, update the dueDate and returns true.
     * @param nextDate
     * @return true if the bill is not paid yet.
     */
    public boolean setDueDate(Date nextDate){
        if(isPaid()||nextDate==null) return false;
        else{
            dueDate = nextDate.clone();
        }
        return true;
    }

    /**
     * This method sets an amount. If it is already paid, it returns false.
     * If not, change the amount and returns true.
     * @param amount
     * @return true, if it is not paid.
     */
    public boolean setAmount(Money amount){
        if(isPaid()||amount==null) return false;
        else{
            this.amount = amount.clone();
        }
        return true;
    }

    /**
     * This method sets an originator.
     * @param originator
     */
    public void setOriginator(String originator){
        this.originator = originator;
    }

    /**
     * Override to print out the details of a bill.
     * @return amount, dueDate, originator, and paidDate.
     */
    @Override
    public String toString() {
        String retVal = "";
        if(isPaid()){
            retVal = "Amount is:"+amount+", Due date:"+dueDate+", To: "+originator+", Paid date:"+paidDate;
        }
        else if(paidDate==null){
            retVal = "Amount is:"+amount+", Due date:"+dueDate+", To: "+originator+", You have not paid yet!";
        }
        return retVal;
    }

    /**
     * Override equals method. Check only.
     * @param toCompare change it to that
     * @return true if they are the same.
     */
    @Override
    public boolean equals(Object toCompare){
        if(!(toCompare instanceof Bill)) return false;
        Bill that = (Bill) toCompare;
        return this.amount.equals(that.amount) && this.dueDate.equals(that.dueDate) && this.originator.equals(that.originator);
    }

    /**
     * Override the compareTo method from the Comparable class.
     * For bill, compare the dueDate only.
     * @param obj that takes only Bill class
     * @return
     */
    @Override
    public int compareTo(Bill obj) {
        return this.dueDate.compareTo(obj.dueDate);
    }
}
