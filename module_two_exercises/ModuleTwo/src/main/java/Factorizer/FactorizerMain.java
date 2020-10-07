//@author Matthew Shoemate

package Factorizer;

import java.util.Scanner;

public class FactorizerMain
{
    public static void main(String[] args)
    {
        FactorizerConfig factorizedNum = new FactorizerConfig(InputNumber());
        DisplayNumbers(factorizedNum);
    }
    
    private static int InputNumber()
    {
        boolean goodInput = false;
        int inputNum = 0;
        Scanner inputReader = new Scanner(System.in);
        
        while(!goodInput)
        {
            System.out.println("What number would you like to factor?");
        
            try
            {
                inputNum = Integer.parseInt(inputReader.nextLine());
                
                if(inputNum > 0)
                {
                    goodInput = true;
                } else
                {
                    System.out.println("\nThe number you entered must be greater than 0.");
                }
            } catch(NumberFormatException e)
            {
                System.out.println("\nYou did not enter a whole number.");
            }
        }
        
        
        return inputNum;
    }
    
    private static void DisplayNumbers(FactorizerConfig factor)
    {
        System.out.println(factor.toString());
    }
}