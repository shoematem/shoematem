//@author Matthew Shoemate

package ClassModeling;

public class IceCream
{
    private String controlSystem;
    private String stockSystem;
    
    public IceCream(){}
    
    public IceCream(String inControlSystem, String inStockSystem)
    {
        this.controlSystem = inControlSystem;
        this.stockSystem = inStockSystem;
    }
    
    public String getControlSystem()
    {
        return controlSystem;
    }
    
    public void setControlSystem(String controlSystem)
    {
        this.controlSystem = controlSystem;
    }
    
    public String getStockSystem()
    {
        return stockSystem;
    }
    
    public void setStockSystem(String stockSystem)
    {
        this.stockSystem = stockSystem;
    }
}