//@author Matthew Shoemate

package PracticeProgramming3;

import java.util.Scanner;

public class MiniMadLibs
{
    public static void main(String[] args)
    {
        String one, two, three, four, five, six, seven, eight, nine, ten;
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Let's play MAD LIBS! \n\n\nI need a noun: ");
        one = inputReader.nextLine();
        
        System.out.println("Now an adjective: ");
        two = inputReader.nextLine();
        
        System.out.println("Another noun: ");
        three = inputReader.nextLine();
        
        System.out.println("And a number: ");
        four = inputReader.nextLine();
        
        System.out.println("Another adjective: ");
        five = inputReader.nextLine();
        
        System.out.println("A plural noun: ");
        six = inputReader.nextLine();
        
        System.out.println("Another one: ");
        seven = inputReader.nextLine();
        
        System.out.println("One more: ");
        eight = inputReader.nextLine();
        
        System.out.println("A verg but present tense: ");
        nine = inputReader.nextLine();
        
        System.out.println("Same verg but pase tense: ");
        ten = inputReader.nextLine();
        
        System.out.println("*** NOW LETS GET MAD (libs) ***\n" + one + ": the " + two + " frontier. These are the voyages of the starship " 
        + three + ". " + "Its " + four + "-year mission: to explore strange " + five + " " + six + ", to seek out " + five + " " + seven
        + " and " + five + " " + eight + ", to boldly " + nine + " where no one has " + ten + " before.");
    }
}