//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class BiggerBetterAdder
{
    public static void main(String[] args)
    {
        int numberOne, numberTwo, numberThree, sum;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Please input the first number to add. ");
        numberOne = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Please input the second number to add. ");
        numberTwo = Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Please input the third number to add. ");
        numberThree = Integer.parseInt(inputReader.nextLine());
        
        sum = numberOne + numberTwo + numberThree;
        
        System.out.println(numberOne + " + " + numberTwo + " + " + numberThree + " = " + sum);
    }
}