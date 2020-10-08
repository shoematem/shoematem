/**
 * @author Matthew Shoemate
 */

package StateCapitalsTwo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class StateCapitals
{
    public static void main(String[] args) throws FileNotFoundException
    {
        int counter = 1, score = 0;
        Map<String, String> stateCaps = new HashMap<>();
        Scanner fromFile = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
        Scanner userInput = new Scanner(System.in);
        String userChoice;
        
        //go through the file line by line
        while(fromFile.hasNextLine())
        {
            String currentLine = fromFile.nextLine();
            String[] array = currentLine.split("::");
            stateCaps.put(array[0], array[1]);
        }
        
        System.out.println(stateCaps.size() + " STATES & CAPITALS ARE LOADED.");
        System.out.println("------------------------");
        System.out.println("HERE ARE THE STATES");
        
        Set<String> stateCapsKeys = stateCaps.keySet();
        
        for(String sC : stateCapsKeys)
        {
            System.out.println(sC);
        }
        
        System.out.println("\nReady to test your knowledge? How many states would you like to guess?");
        userChoice = userInput.nextLine();
        int choice = Integer.parseInt(userChoice);
        
        while(counter <= choice)
        {
            Object randomState = stateCaps.keySet().toArray()[new Random().nextInt(stateCaps.keySet().toArray().length)];
            System.out.println("\nWhat is the capital of " + randomState.toString() + "?");
            userChoice = userInput.nextLine();
        
            if(userChoice.equals(stateCaps.get(randomState)))
            {
                System.out.println(userChoice + " is correct!");
                score++;
            } else
            {
                System.out.println(userChoice + " is incorrect! The correct answer is " + stateCaps.get(randomState));
                score--;
            }
            
            stateCaps.remove(randomState);
            counter++;
        }
        
        System.out.println("Your score was " + score);
    }
}