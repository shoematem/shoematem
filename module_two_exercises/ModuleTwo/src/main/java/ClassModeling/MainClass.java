//@author Matthew Shoemate

package ClassModeling;

public class MainClass
{
    public static void main(String[] args)
    {
        Airplane myAirplane = new Airplane("control","sim");
        Book myBook = new Book("My publishing system", "library catalog");
        Car myCar = new Car("inventory system", "GTA");
        House myHouse = new House(3.52,"yes");
        IceCream myIceCream = new IceCream("control", "stock");
    }
}