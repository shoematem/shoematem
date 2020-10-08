//@author Matthew Shoemate

package InterestCalculator;

import java.util.Scanner;

public class InterestCalculator
{
    public static void main(String[] args)
    {
        double amountInvest, interestRate, principleEarned, earnedMoney;
        int numYears;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How much do you want to invest?");
        amountInvest = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("How many years are you investing?");
        numYears = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("What is the annual interest rate % growth?");
        interestRate = Integer.parseInt(inputReader.nextLine());
        
        for(int i = 1; i <= numYears; i++)
        {
            System.out.println("\nYear = " + i);
            System.out.println("Began with " + amountInvest);
            
            earnedMoney = InterestEarned(amountInvest, interestRate);
            principleEarned = earnedMoney - amountInvest;
            principleEarned = (double) Math.round(principleEarned * 100) / 100;
            
            System.out.println("Earned " + principleEarned);
            System.out.println("Ended with " + earnedMoney);
            
            amountInvest = earnedMoney;
        }
    }
    
    public static double InterestEarned(double amountInvest, double interestRate)
    {
        double total = 0;
        
        interestRate = interestRate / 4;
        
        for(int i = 1; i <= 4; i++)
        {
            total = amountInvest * (1 + (interestRate / 100));
            amountInvest = total;
        }
        
        total = (double) Math.round(total * 100) / 100;
        return total;
    }
}