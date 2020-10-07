/**
 * @author Matthew Shoemate
 */

package DaysUntilFriday;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        String input;
        int daysUntilFriday;
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What day is it?");
        input = userInput.nextLine();
        daysUntilFriday = CalcDaysUntilFriday(DaysOfWeek.valueOf(input.toUpperCase()));
        
        if(daysUntilFriday != 0)
        {
            System.out.println(input + " is " + daysUntilFriday + " days away.");
        } else
        {
            System.out.println("Friday? It's already Friday!");
        }
    }
    
    public enum DaysOfWeek
    {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    public static int CalcDaysUntilFriday(DaysOfWeek days)
    {
        int numDaysUntilFriday = 0;
        
        switch(days)
        {
            case SUNDAY:
                numDaysUntilFriday = 5;
                break;
            case MONDAY:
                numDaysUntilFriday = 4;
                break;
            case TUESDAY:
                numDaysUntilFriday = 3;
                break;
            case WEDNESDAY:
                numDaysUntilFriday = 2;
                break;
            case THURSDAY:
                numDaysUntilFriday = 1;
                break;
            case FRIDAY:
                numDaysUntilFriday = 0;
                break;
            case SATURDAY:
                numDaysUntilFriday = 6;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        
        return numDaysUntilFriday;
    }
}