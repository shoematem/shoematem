//@author Matthew Shoemate

package PracticeProgramming4;

import java.util.Scanner;

public class BirthStones
{
    public static void main(String[] args)
    {
        int monthNum;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What month's birthstone do you want to know? ");
        monthNum = Integer.parseInt(inputReader.nextLine());
        
        if(monthNum == 1)
        {
            System.out.println("January's birthstone is Garnet");
        } else if(monthNum == 2)
        {
            System.out.println("February's birthstone is Amethyst");
        } else if(monthNum == 3)
        {
            System.out.println("March's birthstone is Aquamarine");
        } else if(monthNum == 4)
        {
            System.out.println("April's birthstone is Diamond");
        } else if(monthNum == 5)
        {
            System.out.println("May's birthstone is Emerald");
        } else if(monthNum == 6)
        {
            System.out.println("June's birthstone is Pearl");
        } else if(monthNum == 7)
        {
            System.out.println("July's birthstone is Ruby");
        } else if(monthNum == 8)
        {
            System.out.println("August's birthstone is Preidot");
        } else if(monthNum == 9)
        {
            System.out.println("September's birthstone is Sapphire");
        } else if(monthNum == 10)
        {
            System.out.println("October's birthstone is Opal");
        } else if(monthNum == 11)
        {
            System.out.println("November's birthstone is Topaz");
        } else if(monthNum == 12)
        {
            System.out.println("December's birthstone is Turquoise");
        } else
        {
            System.out.println("I think you must be confused, " + monthNum + " doesn't match a month.");
        }
    }
}