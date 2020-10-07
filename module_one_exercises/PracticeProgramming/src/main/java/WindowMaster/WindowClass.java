// @author Matthew Shoemate

package WindowMaster;
import java.util.Scanner;

public class WindowClass
{
    public static void main(String[] args)
    {   
        //declare variables for the dimensions (height & width) and calculations
        float height, width, areaOfWindow, totalCost, perimeterOfWindow, glassCost, trimCost;
        
        //declare String variables for user's height, width, glassCost, and trimCost input
        String stringHeight, stringWidth, stringGlassCost, stringTrimCost;
        
        //declare and initialize the Scanner
        Scanner myScanner = new Scanner(System.in);
        
        //user input
        System.out.println("Please enter window height:");  
        stringHeight = myScanner.nextLine();
        
        System.out.println("Please enter window width:");
        stringWidth = myScanner.nextLine();
        
        System.out.println("Please enter the cost of the window:");
        stringGlassCost = myScanner.nextLine();
        
        System.out.println("Please enter the cost of the trim:");
        stringTrimCost = myScanner.nextLine();
        
        //convert String values to float values
        height = Float.parseFloat(stringHeight);
        width = Float.parseFloat(stringWidth);
        glassCost = Float.parseFloat(stringGlassCost);
        trimCost = Float.parseFloat(stringTrimCost);
        
        //calculate the area of the window
        areaOfWindow = height * width;
        
        //calculate the perimeter of the window
        perimeterOfWindow = 2 * (height + width);
        
        //calculate the total cost
        totalCost = ((glassCost * areaOfWindow) + (trimCost * perimeterOfWindow));
        
        //display the results to the user
        System.out.println("Window height = " + stringHeight);
        System.out.println("Window width = " + stringWidth);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total cost = " + totalCost);
    }
}