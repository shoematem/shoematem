//@author Matthew Shoemate

package PracticeProgramming2;

public class MenuOfChampions
{
    public static void main(String[] args)
    {
        final String dollarSign = "$";
        String foodOne, foodTwo, foodThree;
        double foodOnePrice, foodTwoPrice, foodThreePrice;
        
        foodOne = "Cheeseburger";
        foodTwo = "Babyback Ribs";
        foodThree = "Baked Mac & Cheese";
        
        foodOnePrice = 7.99;
        foodTwoPrice = 16.99;
        foodThreePrice = 12.99;
        
        System.out.println("Welcome to Example Restaurant!");
        System.out.println("Today on the menu is...");
        System.out.println("**********************");
        System.out.print(foodOne + " --- " + dollarSign + foodOnePrice
        + "\n" + foodTwo + " --- " + dollarSign + foodTwoPrice
        + "\n" + foodThree + " --- " + dollarSign + foodThreePrice);
    }
}