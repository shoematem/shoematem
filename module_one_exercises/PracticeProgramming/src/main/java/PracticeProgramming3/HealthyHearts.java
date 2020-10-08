//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class HealthyHearts
{
    public static void main(String[] args)
    {
        int age, minTargetZone, maxTargetZone, maxHeartRate;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What is your age? ");
        age = Integer.parseInt(inputReader.nextLine());
        
        maxHeartRate = 220 - age;
        minTargetZone = (int)(maxHeartRate * 0.5);
        maxTargetZone = (int)(maxHeartRate * 0.85);
        
        System.out.print("Your maximum heart rate should be " + maxHeartRate + " beats per minute."
        + "Your target heart rate zone is " + minTargetZone + " - " + maxTargetZone + " beats per minute.");
    }
}