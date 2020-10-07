//@author Matthew Shoemate

package PracticeProgramming6;

import java.util.Random;

public class MaybeItLovesMe
{
    public static void main(String[] args)
    {
        boolean lovesMe = true;
        
        int daisyPetals;
        
        Random randomizer = new Random();
        
        daisyPetals = randomizer.nextInt((0 + 76) + 1) + 13;
        
        System.out.println("Well here goes nothing...\n");
        
        while(daisyPetals >= 0)
        {
            if((daisyPetals % 2) == 0)
            {
                System.out.println("It LOVES me!");
                lovesMe = true;
            } else
            {
                System.out.println("It loves me NOT!");
                lovesMe = false;
            }
            
            daisyPetals--;
        }
        
        if(lovesMe)
        {
            System.out.println("\nI knew it! It DOES LOVE me!");
        } else
        {
            System.out.println("\nI knew it! It DOESN'T love me!");
        }
    }
}