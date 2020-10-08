//@author Matthew Shoemate

package SimpleCalculator;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        int inputNumber = 0;
        double operandOne = 0.00, operandTwo = 0.00;
        
        while(inputNumber != 5)
        {
            System.out.println("Please choose which operation you want to do (input the number given).\n1) Addition\n2) Subtraction\n3) Multiplication\n4) Division\n5) Exit");
            inputNumber = (int) ExceptionHandle("operation");
            
            if(inputNumber != 5)
            {
                System.out.println("Please input a number, other than 0, for your first operand. ");
                operandOne = ExceptionHandle("operand");
                
                System.out.println("\nPlease input a number, other than 0, for your second operand. ");
                operandTwo = ExceptionHandle("operand");
                
                SimpleCalculator results = new SimpleCalculator(operandOne, operandTwo, inputNumber);
                DisplayOutput(results);
            }
        }
        
        System.out.println("Thank you for using the simple calculator!");
    }
    
    private static double ExceptionHandle(String inputType)
    {
        boolean goodInput = false;
        double number = 0.00;
        Scanner inputReader = new Scanner(System.in);
        
        do
        {
            try
            {
                number = Double.parseDouble(inputReader.nextLine());
                
                if(inputType.equals("operand"))
                {
                    if(number != 0.00)
                    {
                        goodInput = true;
                    } else
                    {
                        System.out.println("\nYou did put in a good number. Please enter a number that is not 0. ");
                    }
                } else
                {
                    if(number < 1 || number > 5)
                    {
                        System.out.println("\nYou entered a number that is not between 1 and 5.");
                        System.out.println("\nPlease choose which operation you want to do (input the number given).\n1) Addition\n2) Subtraction\n3) Multiplication\n4) Division\n5) Exit");
                    } else
                    {
                        goodInput = true;
                    }
                }
            } catch(NumberFormatException e)
            {
                System.out.println("\n" + number + " is not a number. Please input a number.");
            }
        } while(!goodInput);
        
        return number;
    }
    
    private static void DisplayOutput(SimpleCalculator results)
    {
        System.out.println(results.toString());
    }
}