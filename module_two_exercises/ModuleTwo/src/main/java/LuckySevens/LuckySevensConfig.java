//@author Matthew Shoemate

package LuckySevens;

import java.util.Random;

public class LuckySevensConfig
{
    private final int userMoney, countRolls, holdMaxRolls, holdMaxMoney;
    
    public LuckySevensConfig(int inUserMoney)
    {
        int sumRolls, rollResultOne, rollResultTwo, tempHoldMaxMoney, tempUserMoney;
        int tempCountRolls = 0, tempHoldMaxRolls = 0;
        Random diceOne = new Random();
        Random diceTwo = new Random();
        
        tempUserMoney = inUserMoney;
        tempHoldMaxMoney = tempUserMoney;
        
        while(tempUserMoney > 0)
        {
            rollResultOne = diceOne.nextInt(6 - 1 + 1) + 1; //(max - min + 1) + min
            rollResultTwo = diceTwo.nextInt(6 - 1 + 1) + 1; //(max - min + 1) + min
            sumRolls = rollResultOne + rollResultTwo;
            
            tempCountRolls++;
            
            if(sumRolls == 7)
            {
                tempUserMoney += 4;
            } else
            {
                tempUserMoney -= 1;
            }
            
            if(tempUserMoney > tempHoldMaxMoney)
            {
                tempHoldMaxMoney = tempUserMoney;
                tempHoldMaxRolls = tempCountRolls;
            }
        }
        
        countRolls = tempCountRolls;
        holdMaxRolls = tempHoldMaxRolls;
        holdMaxMoney = tempHoldMaxMoney;
        userMoney = tempUserMoney;
    }
    
    public LuckySevensConfig()
    {
        userMoney = 0;
        countRolls = 0;
        holdMaxRolls = 0;
        holdMaxMoney = 0; 
    }
    
    public double getUserMoney()
    {
        return userMoney;
    }
    
    public int getCountRolls()
    {
        return countRolls;
    }
    
    public int getHoldMaxRolls()
    {
        return holdMaxRolls;
    }
    
    public double getHoldMaxMoney()
    {
        return holdMaxMoney;
    }
    
    @Override
    public String toString()
    {
        String formatOutput;
        formatOutput = "\n\nYou are broke after " + countRolls + " rolls.";
        formatOutput += "\nYou should have quit after " + holdMaxRolls + " when you had $" + holdMaxMoney + ".";
                
        return formatOutput;
    }
}