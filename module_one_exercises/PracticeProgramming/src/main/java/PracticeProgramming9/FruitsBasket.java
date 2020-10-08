//@author Matthew Shoemate

package PracticeProgramming9;

public class FruitsBasket
{
    public static void main(String[] args)
    {
        String[] fruitBasket = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", 
            "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange",
            "Apple", "Apple", "Apple", "Banana", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange",
            "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "PawPaw", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange",
            "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Kiwi", "Orange", "Apple", "Orange", "Dragonfruit", "Apple",
            "Orange", "Orange"};
        
        int numApples = 0;
        int numOtherFruit = 0;
        int numOranges = 0;
        int totalFruits = 0;
        
        for(int i = 0; i < fruitBasket.length; i++)
        {
            if(fruitBasket[i] == "Apple")
            {
                numApples++;
            } else if(fruitBasket[i] == "Orange")
            {
                numOranges++;
            } else
            {
                numOtherFruit++;
            }
            
            totalFruits++;
        }
        
        System.out.println("Total # of Fruit in Basket: " + totalFruits);
        System.out.println("Number of Apples: " + numApples);
        System.out.println("Number of Oranges: " + numOranges);
        System.out.println("Number of Other Fruit: " + numOtherFruit);
    }
}