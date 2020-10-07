//@author Matthew Shoemate
package PracticeProgramming6;

import java.util.Scanner;

public class BewareTheKraken
{
    public static void main(String[] args)
    {
        String swimDecision;
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");
        
        int depthDivedInFt = 0;
        
        //Turns out the ocean is only so deep, 36200 at the deepest survey,
        //so if we reach the bottom ... we should probably stop.
        
        while(depthDivedInFt < 36200)
        {
            System.out.println("So far, we've swum " + depthDivedInFt + " feet");
            
            System.out.println("Would you like to stop swimming? (y/n) ");
            swimDecision = inputReader.nextLine();
            
            if(swimDecision.equals("n"))
            {
                if(depthDivedInFt >= 20000)
                {
                    System.out.println("Uhhh, I think I see a Kraken, guys ....");
                    System.out.println("TIME TO GO!");
                    break;
                }
            } else
            {
                break;
            }
            
            //I can swim, really fast! 50ft at a time!
            depthDivedInFt += 1000;
        }
        
        System.out.println("\nWe ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}