//@author Matthew Shoemate

package PracticeProgramming9;

public class FruitSalad
{
    public static void main(String[] args)
    {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato",
            "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange",
            "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango",
            "Satsuma Orange", "Watermelon", "Snozzberry"};
        
        String[] fruitSalad = new String[12];
        
        int totalFruit = 0;
        int numApples = 0;
        int numOranges = 0;
        int a = 0;
        
        for(int i = 0; i < fruit.length; i++)
        {
            if(totalFruit < 12)
            {
                if(fruit[i].contains("Apple"))
                {
                    numApples++;
                
                    if(numApples <= 3)
                    {
                        fruitSalad[totalFruit] = fruit[i];
                        System.out.println(fruitSalad[totalFruit]);
                        totalFruit++;
                        a++;
                    }
                } else if(fruit[i].contains("Orange"))
                {
                    numOranges++;
                
                    if(numOranges <= 2)
                    {
                        fruitSalad[totalFruit] = fruit[i];
                        System.out.println(fruitSalad[totalFruit]);
                        totalFruit++;
                    }
                } else if(fruit[i].contains("berry"))
                {
                    fruitSalad[totalFruit] = fruit[i];
                    System.out.println(fruitSalad[totalFruit]);
                    totalFruit++;
                } else if(!fruit[i].contains("Tomato"))
                {
                    fruitSalad[totalFruit] = fruit[i];
                    System.out.println(fruitSalad[totalFruit]);
                    totalFruit++;
                }
            }
        }
        
        System.out.println("Total number of fruit: " + totalFruit);
        System.out.println("Total number of apples: " + a);
        System.out.println("Total number of oranges: " + numOranges);
        System.out.println("Total number of other fruit: " + (totalFruit - (a + numOranges)));
    }
}