//@author Matthew Shoemate

package ClassModeling;

public class HouseGPS
{
    private int xCoordinate;
    private int yCoordinate;
    private int[] favorites;
    private String address;
    
    public HouseGPS(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.address = address;
        this.favorites = favorites;
    }
    
    public int[] getFavorites()
    {
        return favorites;
    }
    
    public int getXCoordinate()
    {
        return xCoordinate;
    }
    
    public int getYCoordinate()
    {
        return yCoordinate;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setXCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }
    
    public void setYCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
}