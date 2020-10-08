//@author Matthew Shoemate

package ClassModeling;

public class House
{
    private double GPS;
    private String threeD;
    
    public House(){}
    
    public House(double inputGPS, String input3D)
    {
        this.GPS = inputGPS;
        this.threeD = input3D;
    }
    
    public double getGPS()
    {
        return GPS;
    }
    
    public void setGPS(double GPS)
    {
        this.GPS = GPS;
    }
    
    public String get3D()
    {
        return threeD;
    }
    
    public void set3D(String threeD)
    {
        this.threeD = threeD;
    }
}