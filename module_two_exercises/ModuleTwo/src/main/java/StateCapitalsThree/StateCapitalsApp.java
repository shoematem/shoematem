/**
 * @author Matthew Shoemate
 */
package StateCapitalsThree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StateCapitalsApp
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String userChoice = "";
        Object[] capVals = new Object[50];
        Map<String, Object> stateCaps = new HashMap<>();
        Scanner fromFile = new Scanner(new BufferedReader(new FileReader("MoreStateCapitals.txt")));
        Scanner userInput = new Scanner(System.in);
        Capital cap = new Capital();
        
        //go through the file line by line
        while(fromFile.hasNextLine())
        {
            String currentLine = fromFile.nextLine();
            String[] array = currentLine.split("::");
            stateCaps.put(array[0], array[1] + "/" + array[2] + "/" + array[3]);
        }
        
        System.out.println(stateCaps.size() + " STATES & CAPITALS ARE LOADED.");
        System.out.println("------------------------");
        System.out.println("HERE ARE THE STATES");
        
        Set<String> stateCapsKeys = stateCaps.keySet();
        int i = 0;
        for(String sC : stateCapsKeys)
        {
            System.out.println(sC + " - " + stateCaps.get(sC));
            capVals[i] = stateCaps.get(sC);
            i++;
        }
        
        System.out.println("\nPlease input a population limit to see how many are above it.");
        userChoice = userInput.nextLine();
        cap.PopulationLimit(capVals, userChoice);
        
        System.out.println("\nPlease input an upper limit for capital city square mileage.");
        userChoice = userInput.nextLine();
        cap.CitySquareMileage(capVals, userChoice);
    }
}