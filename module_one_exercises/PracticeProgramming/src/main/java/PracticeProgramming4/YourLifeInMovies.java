//@author Matthew Shoemate

package PracticeProgramming4;

import java.util.Scanner;

public class YourLifeInMovies
{
    public static void main(String[] args)
    {
        int age, ageYearDiff;
        String name;
    
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Hey, let's play a game! What's your name? ");
        name = inputReader.nextLine();
        
        System.out.println("Ok, " + name + ", when were you born? ");
        age = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Well Kristen...");
        
        ageYearDiff = 2020 - age;
        
        if(ageYearDiff < 2005)
        {
            System.out.println("Did you know that Pixar's 'Up' came out over a decade ago?");
        }
        
        if(ageYearDiff < 1995)
        {
            System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        
        if(ageYearDiff < 1985)
        {
            System.out.println("Also, Space Jam came out not last decade, but the one before THAT");
        }
        
        if(ageYearDiff < 1975)
        {
            System.out.println("The original Jurassic Park release is closer to the first lunar landing than it is today!");
        }
        
        if(ageYearDiff < 1965)
        {
            System.out.println("Incredibly, the MASH TV series has been around for almost a half century");
        }
    }
}