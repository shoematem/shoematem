//@author Matthew Shoemate

package InterestCalculator;

import java.util.Scanner;

public class InterestCalculatorMain
{
    public static void main(String[] args)
    {
        double amountInvest, interestRate;
        int numYears;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How much do you want to invest? ");
        amountInvest = Double.parseDouble(inputReader.nextLine());
        
        System.out.println("How many years do you want to invest? ");
        numYears = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("What is the annual interest rate % growth?");
        interestRate = Double.parseDouble(inputReader.nextLine());
        
        InterestCalculatorConfig results = new InterestCalculatorConfig(amountInvest, numYears, interestRate);
        DisplayOutput(results);
    }
    
    private static void DisplayOutput(InterestCalculatorConfig results)
    {
        System.out.println(results.toString());
    }
}