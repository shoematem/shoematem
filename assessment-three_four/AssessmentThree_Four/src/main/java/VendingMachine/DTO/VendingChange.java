/**
 * VendingChange - this class uses enums for the coins and assigns it values. This
 * class has methods inside to calculate the difference between the user money and
 * the money given back to the user
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */

package VendingMachine.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class VendingChange
{
    private String userMoney;
    private int numPennies = 0, numNickels = 0, numDimes = 0, numQuarters = 0;
    
    /**
     * MoneyValues - an enum that declares the coins (Quarter, Dime, Nickel,
     * Penny) and assigns them values. Inside the enum is a constructor to set
     * the value
     */
    public enum MoneyValues
    {
        QUARTER(new BigDecimal("0.25")),
        DIME(new BigDecimal("0.10")),
        NICKEL(new BigDecimal("0.05")),
        PENNY(new BigDecimal("0.01"));
        
        private final BigDecimal value;
        
        MoneyValues(BigDecimal value)
        {
            this.value = value;
        }
        
        public BigDecimal value()
        {
            return value;
        }
    }
    
    /**
     * VendingChange - a default constructor
     */
    public VendingChange(){}
   
    /**
     * VendingChange - a constructor that brings in the user money and sets the
     * user money to be used throughout the program
     * -------------------------------------------------------------------------
     * @param userMoney - the user's money
     */
    public VendingChange(String userMoney)
    {
        this.userMoney = userMoney;
    }
    
    /**
     * CalcUserMoney - this method calculates the user's money. This is for if
     * the user already input money into the system and needed to enter more money.
     * It adds the original money with the newly inputted money
     * -------------------------------------------------------------------------
     * @param origUserMoney - the original money in the same
     * @param newUserMoney - the money the user has just input into the system
     */
    public void CalcUserMoney(String origUserMoney, String newUserMoney)
    {
        BigDecimal money;
        BigDecimal oUserMoney = new BigDecimal(origUserMoney);
        BigDecimal nUserMoney = new BigDecimal(newUserMoney);
        
        oUserMoney = oUserMoney.setScale(2, RoundingMode.HALF_UP); //sets the scale to round up to the 2nd decimal point
        nUserMoney = nUserMoney.setScale(2, RoundingMode.HALF_UP); //sets the scale to round up to the 2nd decimal point
        
        money = oUserMoney.add(nUserMoney); //adds the original money and new user money
        
        setUserMoney(money.toPlainString()); //sets added money to the field userMoney
    }
   
    /**
     * CalcChange - this method calculates the change between the user money and 
     * drink price. This is regardless if the user money input is less than the
     * drink price or not
     * -------------------------------------------------------------------------
     * @param drinkPrice - passes the drink price value
     * @return - the difference of the drink price and user money
     */
    public String CalcChange(String drinkPrice)
    {   
        String returnVal;
        int[] moneyBack = new int[4];
        BigDecimal difference;
        BigDecimal price = new BigDecimal(drinkPrice);
        BigDecimal enteredMoney = new BigDecimal(userMoney);
        
        price = price.setScale(2, RoundingMode.HALF_UP); //sets the scale to round up to the 2nd decimal point
        enteredMoney = enteredMoney.setScale(2, RoundingMode.HALF_UP); //sets the scale to round up to the 2nd decimal point
        
        difference = enteredMoney.subtract(price); //substracts the user money by the drink price

        //this checks if the sign of the number is -, 0, or + (-1, 0, 1 for the value of signum)
        if(difference.signum() == 1)
        {
            //loops while the difference is greater than the value of the quarter
            while(difference.compareTo(MoneyValues.QUARTER.value()) >= 0)
            {
                numQuarters++;
                difference = difference.subtract(MoneyValues.QUARTER.value());
            }
            
            //loops while the difference is greater than the value of the dime
            while(difference.compareTo(MoneyValues.DIME.value()) >= 0)
            {
                numDimes++;
                difference = difference.subtract(MoneyValues.DIME.value());
            }
            
            //loops while the difference is greater than the value of the nickel
            while(difference.compareTo(MoneyValues.NICKEL.value()) >= 0)
            {
                numNickels++;
                difference = difference.subtract(MoneyValues.NICKEL.value());
            }
            
            //loops while the difference is greater than the value of the pennies
            while(difference.compareTo(MoneyValues.PENNY.value()) >= 0)
            {
                numPennies++;
                difference = difference.subtract(MoneyValues.PENNY.value());
            }
            
            moneyBack[0] = numQuarters;
            moneyBack[1] = numDimes;
            moneyBack[2] = numNickels;
            moneyBack[3] = numPennies;
            
            returnVal = Arrays.toString(moneyBack);
        } else
        {
            returnVal = difference.toPlainString();
        }
        
        return returnVal;
    }
   
    /**
     * getUserMoney - getter for the field userMoney
     * -------------------------------------------------------------------------
     * @return - returns the user money that was set
     */
    public String getUserMoney()
    {
        return userMoney;
    }

    /**
     * setUserMoney - setter for the field userMoney
     * -------------------------------------------------------------------------
     * @param userMoney - brings in the user money
     */
    public void setUserMoney(String userMoney)
    {
        this.userMoney = userMoney;
    }
}