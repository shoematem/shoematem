//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class AllTheTrivia
{
    public static void main(String[] args)
    {
        String answerOne, answerTwo, answerThree, answerFour;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What unit is equivalent to 1,024 Gigabytes? ");
        answerOne = inputReader.nextLine();
        
        System.out.println("Which planet is the only one that rotates clockwise in our Solar System? ");
        answerTwo = inputReader.nextLine();
        
        System.out.println("The largest volcano ever discovered in our Solar System is located on which planet? ");
        answerThree = inputReader.nextLine();
        
        System.out.println("What is the most abundant element in the Earth's atmosphere? ");
        answerFour = inputReader.nextLine();
        
        System.out.println("Wow, 1,024 Gigabytes is a " + answerFour);
        System.out.println("I didn't know that the largest ever volcano was discovered on " + answerTwo);
        System.out.println("That's amazing that " + answerThree + " is the most abundant element in the atmosphere...");
        System.out.println(answerOne + " is the only planet that rotates clockwise around the sun, neat!");
    }
}