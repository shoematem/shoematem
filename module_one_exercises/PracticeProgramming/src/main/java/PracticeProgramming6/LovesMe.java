//@author Matthew Shoemate

package PracticeProgramming6;

public class LovesMe
{
    public static void main(String[] args)
    {
        boolean lovesMe = true;
        int rosePetals = 34;
        
        System.out.println("Well here goes nothing...\n");
        
        while(rosePetals >= 0)
        {
            if((rosePetals % 2) == 0)
            {
                System.out.println("It loves me NOT!");
                lovesMe = false;
            } else
            {
                System.out.println("It LOVES me!");
                lovesMe = true;
            }
            
            rosePetals--;
        }
        
        if(lovesMe)
        {
            System.out.println("\nI knew it! It LOVES ME!");
        } else
        {
            System.out.println("\nI knew it! It DOESN'T love me!");
        }
    }
}