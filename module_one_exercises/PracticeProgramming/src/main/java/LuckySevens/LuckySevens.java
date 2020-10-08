//@author Matthew Shoemate

package LuckySevens;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens
{
    public static void main(String[] args)
    {
        int userMoney = 0, countRolls = 0, holdMaxMoney = 0, holdRolls = 0;
        int rollResultOne, rollResultTwo, sumRolls;
        
        Random diceOne = new Random();
        Random diceTwo = new Random();
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many dollars do you have?");
        userMoney = Integer.parseInt(inputReader.nextLine());
        holdMaxMoney = userMoney;
        
        while(userMoney > 0)
        {
            countRolls++;
            
            rollResultOne = diceOne.nextInt(6 - 1 + 1) + 1; //(max - min + 1) + min
            rollResultTwo = diceTwo.nextInt(6 - 1 + 1) + 1; //(max - min + 1) + min
            sumRolls = rollResultOne + rollResultTwo;
            
            if(sumRolls == 7)
            {
                userMoney += 4;
            } else
            {
                userMoney -= 1;
            }
            
            if(userMoney > holdMaxMoney)
            {
                holdMaxMoney = userMoney;
                holdRolls = countRolls;
            }
        }
        
        System.out.println("You are broke after " + countRolls + " rolls.");
        System.out.println("You should have quit after " + holdRolls + " rolls when you had $" + holdMaxMoney + ".");
    }
}