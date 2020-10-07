//@author Matthew Shoemate

package PracticeProgramming7;

import java.util.Scanner;

public class ForTimesFor
{
    public static void main(String[] args)
    {
        double percRight;
        int num, multAnswer, userAnswer;
        int userPoints = 0;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Which times table shall you recite? ");
        num = Integer.parseInt(inputReader.nextLine());
        
        for(int i = 1; i <= 15; i++)
        {
            multAnswer = i * num;
            
            System.out.println("\n" + i + " * " + num + " is: ");
            userAnswer = Integer.parseInt(inputReader.nextLine());
            
            if(multAnswer == userAnswer)
            {
                System.out.println("That is correct! The answer is " + multAnswer);
                userPoints++;
            } else
            {
                System.out.println("That is incorrect! The answer is " + multAnswer);
            }
        }
        
        System.out.println("The total correct answers you got were " + userPoints);
        
        percRight = (userPoints / 15.0) * 100;
        
        if(percRight < 50)
        {
            System.out.println("Ouch. You got below a 50%. You should probably study a little bit more.");
        } else if(percRight > 90)
        {
            System.out.println("Congratulations! You got over 90% correct!");
        }
    }
}