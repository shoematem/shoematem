//@author Matthew Shoemate

package PracticeProgramming5;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore
{
    public static void main(String[] args)
    {
        boolean correctAnswer = false;
        int numGuess, randomNum;
        
        Random randomizer = new Random();
        Scanner inputReader = new Scanner(System.in);
        
        randomNum = randomizer.nextInt((0 + 200) + 1) - 100;
       
        System.out.println("I've chosen a number between -100 and 100. Bet you can't guess it!");
        System.out.println("Your guess: ");
        numGuess = Integer.parseInt(inputReader.nextLine());
        
        while(!correctAnswer)
        {
            if(numGuess < randomNum)
            {
                System.out.println("Ha, nice try - too low! Try again!");
                numGuess = Integer.parseInt(inputReader.nextLine());
            } else if(numGuess == randomNum)
            {
                System.out.println("Wow, nice guess! That was it!");
                correctAnswer = true;
            } else
            {
                System.out.println("Ha, nice try - too high! Try again!");
                numGuess = Integer.parseInt(inputReader.nextLine());
            }
        }
    }
}