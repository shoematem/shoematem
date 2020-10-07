//@author Matthew Shoemate

package ClassModeling;

public class Car
{
    private String inventorySystem;
    private String videoGame;
    
    public Car(){}
    
    public Car(String inInvSystem, String inVideoGame)
    {
        this.inventorySystem = inInvSystem;
        this.videoGame = inVideoGame;
    }
    
    public String getInventorySystem()
    {
        return inventorySystem;
    }
    
    public void setInventorySystem(String inventorySystem)
    {
        this.inventorySystem = inventorySystem;
    }
    
    public String getVideoGame()
    {
        return videoGame;
    }
    
    public void setVideoGame(String videoGame)
    {
        this.videoGame = videoGame;
    }
}