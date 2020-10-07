//@author Matthew Shoemate

package PracticeProgramming5;

import java.util.Random;
import java.util.Scanner;

public class HighRoller
{
    public static void main(String[] args)
    {
        int numSides;
        
        Random diceRoller = new Random();
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many sides does the single die have? ");
        numSides = Integer.parseInt(inputReader.nextLine());
        
        int rollResult = diceRoller.nextInt(numSides) + 1;
        
        System.out.println("TIME TO ROOOOOOL THE DICE!");
        System.out.println("I roll a " + rollResult);
        
        if(rollResult == 1)
        {
            System.out.println("You rolled a critical failure! Ouch.");
        } else if(rollResult == (numSides + 1))
        {
            System.out.println("You rolled a critical! Nice job!");
        }
    }
}