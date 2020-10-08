//@author Matthew Shoemate

package ClassRoster.UI;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO
{
    private final Scanner userInput;
    private int intNumber;
    private double doubleNumber;
    private float floatNumber;
    private long longNumber;
    private String stringInput;
    
    public UserIOConsoleImpl()
    {
        this.userInput = new Scanner(System.in);
        intNumber = 0;
        doubleNumber = 0;
        floatNumber = 0;
        longNumber = 0;
    }
    
    public int getIntNumber()
    {
        return intNumber;
    }
    
    public double getDoubleNumber()
    {
        return doubleNumber;
    }
    
    public float getFloatNumber()
    {
        return floatNumber;
    }
    
    public long getLongNumber()
    {
        return longNumber;
    }
    
    public String getStringInput()
    {
        return stringInput;
    }
    
    public void setStringInput(String stringInput)
    {
        this.stringInput = stringInput;
    }
    
    public void setIntNumber(int number)
    {
        this.intNumber = number;
    }
    
    public void setDoubleNumber(double number)
    {
        this.doubleNumber = number;
    }
    
    public void setFloatNumber(float number)
    {
        this.floatNumber = number;
    }
    
    public void setLongNumber(long number)
    {
        this.longNumber = number;
    }
    
    @Override
    public void print(String message)
    {
        System.out.println(message);
    }
    
    @Override
    public String readString(String prompt)
    {
        System.out.println("\n" + prompt);
        stringInput = userInput.nextLine();
        
        return stringInput;
    }
    
    @Override
    public int readInt(String prompt)
    {
        System.out.println("\n" + prompt);
        intNumber = Integer.parseInt(userInput.nextLine());
        
        return intNumber;
    }
    
    @Override
    public int readInt(String prompt, int min, int max)
    {
        boolean goodInput = false;
        
        while(!goodInput)
        {
            System.out.println("\n" + prompt);
            intNumber = Integer.parseInt(userInput.nextLine());
            goodInput = true;
        }
        
        return intNumber;
    }
    
    @Override
    public double readDouble(String prompt)
    {
        System.out.println("\n" + prompt);
        doubleNumber = Double.parseDouble(userInput.nextLine());
        
        return doubleNumber;
    }
    
    @Override
    public double readDouble(String prompt, double min, double max)
    {
        boolean goodInput = false;
        
        while(!goodInput)
        {
            System.out.println("\n" + prompt);
            doubleNumber = Double.parseDouble(userInput.nextLine());
            goodInput = true;
        }
        
        return doubleNumber;
    }
    
    @Override public float readFloat(String prompt)
    {
        System.out.println("\n" + prompt);
        floatNumber = Float.parseFloat(userInput.nextLine());
        
        return floatNumber;
    }
    
    @Override public float readFloat(String prompt, float min, float max)
    {
        boolean goodInput = false;
        
        while(!goodInput)
        {
            System.out.println("\n" + prompt);
            floatNumber = Float.parseFloat(userInput.nextLine());
            goodInput = true;
        }
        
        return floatNumber;
    }
    
    @Override public long readLong(String prompt)
    {
        System.out.println("\n" + prompt);
        longNumber = Long.parseLong(userInput.nextLine());
        
        return longNumber;
    }
    
    @Override
    public long readLong(String prompt, long min, long max)
    {
        boolean goodInput = false;
        
        while(!goodInput)
        {
            System.out.println("\n" + prompt);
            longNumber = Long.parseLong(userInput.nextLine());
            goodInput = true;
        }
        
        return longNumber;
    }
}