//@author Matthew Shoemate

package PracticeProgramming4;

import java.util.Scanner;

public class GuessMe
{
    public static void main(String[] args)
    {
        int userNum;
        int num = 19;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("I've chosen a number. Betcha can't guess it!\nYour guess: ");
        userNum = Integer.parseInt(inputReader.nextLine());
        
        if(userNum == num)
        {
            System.out.println(userNum + "? You guessed my number!");
        } else if(userNum < num)
        {
            System.out.println(userNum + "? Ha, nice try - too low! I chose " + num);
        } else if(userNum > num)
        {
            System.out.println(userNum + "? Ha, nice try - too high! I chose " + num);
        }
    }
}