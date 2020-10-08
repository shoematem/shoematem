//@author Matthew Shoemate

package PracticeProgramming4;

import java.util.Scanner;

public class FieldDay
{
    public static void main(String[] args)
    {
        String personName;
    
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What's your last name? ");
        personName = userInput.nextLine();
                
        if(personName.compareToIgnoreCase("Baggins") < 0)
        {
           System.out.println("Aha! You're on the team 'Red Dragons'!\nGood luck in the games!"); 
        } else if(personName.compareToIgnoreCase("Dresden") < 0)
        {
           System.out.println("Aha! You're on the team 'Dark Wizards'!\nGood luck in the games!"); 
        } else if(personName.compareToIgnoreCase("Howl") < 0)
        {
           System.out.println("Aha! You're on the team 'Moving Castles'!\nGood luck in the games!"); 
        } else if(personName.compareToIgnoreCase("Potter") < 0)
        {
            System.out.println("Aha! You're on the team 'Golden Snitches'!\nGood luck in the games!");
        } else if(personName.compareToIgnoreCase("Vimes") < 0)
        {
            System.out.println("Aha! You're on the team 'Night Guards'!\nGood luck in the games!");
        } else
        {
            System.out.println("Aha! You're on the team 'Black Holes'!\nGood luck in the games!");
        }
    }
}