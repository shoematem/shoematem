//@author Matthew Shoemate

package PracticeProgramming5;

import java.util.Random;

public class CoinFlipper
{
    public static void main(String[] args)
    {
        boolean coinSide;
        
        Random randomizer = new Random();
        
        coinSide = randomizer.nextBoolean();
        
        System.out.println("Ready, Set, Flip...!");
        
        if(coinSide)
        {
            System.out.println("You got HEADS!");
        } else
        {
            System.out.println("You got TAILS!");
        }
    }
}