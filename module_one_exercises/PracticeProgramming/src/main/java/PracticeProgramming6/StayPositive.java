//@author Matthew Shoemate

package PracticeProgramming6;

import java.util.Scanner;

public class StayPositive
{
    public static void main(String[] args)
    {
        int countDown;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What number should I count down from? ");
        countDown = Integer.parseInt(inputReader.nextLine());
        
        if(countDown >= 0)
        {
            System.out.println("Counting down...\n");
        
            while(countDown > -1)
            {
                System.out.println(countDown);
            
                countDown--;
            }
        
            System.out.println("\nBlast off!");
        } else
        {
            System.out.println("That is not a positive number!");
        }
    }
}