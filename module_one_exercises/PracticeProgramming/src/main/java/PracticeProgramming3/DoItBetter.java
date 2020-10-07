//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class DoItBetter
{
    public static void main(String[] args)
    {
        int miles, hotDogs, languages;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many miles can you run? ");
        miles = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Ha! Is that it? I can run " + ((miles * 2) + 1) + " miles!\nHow many hot dogs can you eat? ");
        hotDogs = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("That isn't much! I can eat " + ((hotDogs * 2) + 1) + " hot dogs!\nHow many languages do you know? ");
        languages = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("I know more! I know " + ((languages * 2) + 1) + " languages!");
    }
}