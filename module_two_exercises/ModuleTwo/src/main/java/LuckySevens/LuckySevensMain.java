//@author Matthew Shoemate

package LuckySevens;

import java.util.Random;
import java.util.Scanner;

public class LuckySevensMain
{
    public static void main(String[] args)
    {
        LuckySevensConfig luckySevens = new LuckySevensConfig(UserInput());
        DisplayOutput(luckySevens);
    }
    
    private static int DiceInput()
    {
        int diceVal;
        Random dice = new Random();
        
        diceVal = dice.nextInt(6) + 1;
        
        return diceVal;
    }
    
    private static int UserInput()
    {
        boolean goodInput = false;
        int userInput = 0;
        Scanner inputReader = new Scanner(System.in);
        
        while(!goodInput)
        {
            System.out.println("How many dollars do you have? ");
            
            try
            {
                userInput = Integer.parseInt(inputReader.nextLine());
                goodInput = true;
            } catch(NumberFormatException e)
            {
                System.out.println("\nYou did not input a whole number.");
            }
            
            
        }
        
        return userInput;
    }
    
    private static void DisplayOutput(LuckySevensConfig output)
    {
        System.out.println(output.toString());
    }
}