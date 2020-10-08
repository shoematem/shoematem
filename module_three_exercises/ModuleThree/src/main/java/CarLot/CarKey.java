/**
 * @author Matthew Shoemate
 */

package CarLot;

public class CarKey
{
    private String vin;
    private boolean laserCut;
    
    public String getVin()
    {
        return vin;
    }
    
    public boolean getLaserCut()
    {
        return laserCut;
    }
    
    public void setVin(String vin)
    {
        this.vin = vin;
    }
    
    public void setLaserCut(boolean laserCut)
    {
        this.laserCut = laserCut;
    }
}