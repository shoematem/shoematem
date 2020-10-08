//@author Matthew Shoemate

package PracticeProgramming7;

import java.util.Scanner;

public class TheCount
{
    public static void main(String[] args)
    {
        int startVal, endVal, incrementVal, total;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.println("Start at: ");
        startVal = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Stop at: ");
        endVal = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Count by: ");
        incrementVal = Integer.parseInt(inputReader.nextLine());
        
        total = startVal;
        
        for(int i = 1; total < endVal; i++)
        {
            if((i % 3) == 0)
            {
                System.out.print(total + " - Ah ah ah!\n");
            } else
            {
                System.out.print(total + " ");
            }
            
            total += incrementVal;
        }
    }
}