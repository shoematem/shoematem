//@author Matthew Shoemate

package PracticeProgramming2;

public class AllAboutMe
{
    public static void main(String[] args)
    {
        boolean whistle;
        int numPets;
        String name, favFood, residence;
        
        favFood = "BBQ";
        name = "Matthew Shoemate";
        numPets = 2;
        residence = "apartment";
        whistle = true;
        
        System.out.print("My name is " + name + "."
        + "\nMy favorite food is " + favFood + "."
        + "\nI have " + numPets + " pets."
        + "\nI live in an " + residence + "."
        + "\nIt is " + whistle + " I know how to whistle.");
    }
}