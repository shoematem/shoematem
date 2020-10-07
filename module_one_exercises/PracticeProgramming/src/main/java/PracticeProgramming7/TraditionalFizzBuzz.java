//@author Matthew Shoemate

package PracticeProgramming7;

import java.util.Scanner;

public class TraditionalFizzBuzz
{
    public static void main(String[] args)
    {
        int userAnswer;
        int fizzBuzzCounter = 0;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many units of fizzing and buzzing do you need in your life? ");
        userAnswer = Integer.parseInt(inputReader.nextLine());
        
        for(int i = 0; fizzBuzzCounter <= userAnswer; i++)
        {
            if(i == 15)
            {
                System.out.println("fizz buzz");
                fizzBuzzCounter++;
            } else if((i % 3) == 0)
            {
                System.out.println("fizz");
                fizzBuzzCounter++;
            } else if((i % 5) == 0)
            {
                System.out.println("buzz");
                fizzBuzzCounter++;
            } else
            {
                System.out.println(i);
            }
        }
        
        System.out.println("TRADITION!!!");
    }
}