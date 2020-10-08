//@author Matthew Shoemate

package PracticeProgramming6;

import java.util.Random;

public class LazyTeenager
{
    public static void main(String[] args)
    {
        boolean roomClean = false;
        
        double percentChance = 0.1;
        
        int timesTold = 1;

        Random randomizer = new Random();
        
        do
            {
                if(timesTold == 7)
                {
                    System.out.println("You're grounded and you'll be without your Xbox!");
                    break;
                }
                
                System.out.println("Your mom has told you to clean your room!");
                roomClean = randomizer.nextBoolean();
                
                timesTold++;
            }
        while(roomClean);
        
        if(roomClean)
        {
            System.out.println("FINE! I'LL CLEAN MY ROOM!");
        }
    }
}