//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class PassingTheTuringTest
{
    public static void main(String[] args)
    {
        String color, food, name;
        int number;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Hello! What is your name? ");
        name = inputReader.nextLine();
        
        System.out.println("Hi, " + name + "! I'm Bob. \nWhat's your favorite color? ");
        color = inputReader.nextLine();
        
        System.out.println("Huh, " + color + "? Mine's neon blue!\nI really like cheeseburgers. What is your favorite food, " + name + "? ");
        food = inputReader.nextLine();
        
        System.out.println("Really? " + food + "? That's pretty cool!\nSpeaking of favorites, what's your favorite number? ");
        number = Integer.parseInt(inputReader.nextLine());
        
        System.out.println(number + " is a cool number. Mine's 19.\nDid you know " + number + " * "
        + "19 is " + (number * 19) + "? That's a cool number too!");
        
        System.out.println("Well, thanks for talking to me, " + name);
    }
}