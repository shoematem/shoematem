//@author Matthew Shoemate

package PracticeProgramming7;

import java.util.Scanner;

public class ForTimes
{
    public static void main(String[] args)
    {
        int num, multAnswer, userAnswer;
        
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
                System.out.println("That is correct! The answer is " + userAnswer);
            } else
            {
                System.out.println("That is incorrect! The answer is " + userAnswer);
            }
        }
    }
}