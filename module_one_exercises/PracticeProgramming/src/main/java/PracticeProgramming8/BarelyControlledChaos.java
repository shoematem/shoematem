//@author Matthew Shoemate

package PracticeProgramming8;

import java.util.Random;

public class BarelyControlledChaos
{
    public static void main(String[] args)
    {
        String color = color(); //call color method here
        String animal = animal(); //call animal method here
        String colorAgain = color(); //call color method again here
        int weight = number(5, 200); //call number method with a range between 5 - 200
        int distance = number(10, 20); //call number method with a range between 10 - 20
        int number = number(10000, 20000); //call number method with a range between 10000 - 20000
        int time = number(2, 6); //call number method with a range between 2 - 6
        
        System.out.println("Once, when I was very small...");
        System.out.println("I was chased by a " + color + ", " + weight + "lb " + " miniature " + animal + " for over " + distance + " miles!!");
        System.out.println("I had to hide in a field of over " + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");
        System.out.println("\nIt was QUITE the experience let me tell you!");
    }
    
    public static String color()
    {
        String [] arr = {"red", "white", "blue", "maize", "orange"};
        
        Random randomizer = new Random();
        
        int color = randomizer.nextInt(arr.length);
        
        return arr[color];
    }
    
    public static String animal()
    {
        String [] arr = {"cat", "dog", "zebra", "lion", "owl"};
        
        Random randomizer = new Random();
        
        int animal = randomizer.nextInt(arr.length);
        
        return arr[animal];
    }
    
    public static int number(int numOne, int numTwo)
    {
        int finalNum;
        
        Random randomizer = new Random();
        
        finalNum = randomizer.nextInt((numTwo = numOne) + 1) + numOne;
        return finalNum;
    }
}