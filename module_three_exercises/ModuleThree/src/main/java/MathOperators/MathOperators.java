/**
 * @author Matthew Shoemate
 */

package MathOperators;

import java.util.Scanner;

public class MathOperators
{
    public static void main(String[] args)
    {
        int operandOne, operandTwo;
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Please input a number for operations.");
        operandOne = Integer.parseInt(userInput.nextLine());
        
        System.out.println("Please input another number for operations.");
        operandTwo = Integer.parseInt(userInput.nextLine());
        
        System.out.println(operandOne + " + " + operandTwo + " = " + Calculate(Operators.PLUS, operandOne, operandTwo));
        System.out.println(operandOne + " - " + operandTwo + " = " + Calculate(Operators.MINUS, operandOne, operandTwo));
        System.out.println(operandOne + " * " + operandTwo + " = " + Calculate(Operators.MULTIPLY, operandOne, operandTwo));
        System.out.println(operandOne + " / " + operandTwo + " = " + Calculate(Operators.DIVIDE, operandOne, operandTwo));
    }
    
    public enum Operators
    {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }
    
    public static int Calculate(Operators operator, int operandOne, int operandTwo)
    {
        int answer = 0;
        
        switch(operator)
        {
            case PLUS:
                answer = operandOne + operandTwo;
                break;
            case MINUS:
                answer = operandOne - operandTwo;
                break;
            case MULTIPLY:
                answer = operandOne * operandTwo;
                break;
            case DIVIDE:
                answer = operandOne / operandTwo;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        
        return answer;
    }
}