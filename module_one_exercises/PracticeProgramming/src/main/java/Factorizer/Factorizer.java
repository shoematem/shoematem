//@author Matthew Shoemate

package Factorizer;

import java.util.Scanner;

public class Factorizer
{
    public static void main(String[] args)
    {
        boolean goodInput = false;
        int numCount = 0, runningTotal = 1;
        int numFactor = 0;
        
        Scanner inputReader = new Scanner(System.in);
        
        while(!goodInput)
        {
            System.out.println("What number would you like to factor?");
            
            try
            {
                numFactor = Integer.parseInt(inputReader.nextLine());
                
                if(numFactor > 0)
                {
                    goodInput = true;
                } else
                {
                    System.out.println("\nThe number you entered must be greater than 0.");
                }
            } catch (NumberFormatException e)
            {
                System.out.println("\nYou did not enter a whole number.");
            }
        }
        
        ConfigureNumber(numFactor);
    }
    
    public static void ConfigureNumber(int numFactor)
    {
        int runningTotal = 1, numCount = 0;
        
        for(int i = 2; i < numFactor; i++)
        {
            if((numFactor % i) == 0)
            {
                numCount++;
                runningTotal += i;
                
                if(numCount == 1)
                {
                    System.out.print("\nThe factors of " + numFactor + " are: \n" + 1 + " " + i + " ");
                } else
                {
                    System.out.print(i + " ");
                }
            }
        }
        
        if(numCount > 0)
        {
            System.out.print(numFactor);
            
            if(runningTotal == numFactor)
            {
                //should put this in a boolean method
                System.out.println("\n\n" + numFactor + " is a perfect number.");
            } else
            {
                System.out.println("\n\n" + numFactor + " is not a perfect number.");
            }
            
            System.out.println(numFactor + " is not a prime number.");
        } else
        {
            System.out.println("\n" + numFactor + " is a prime number.");
        }
    }
}