/**
 * @author Matthew Shoemate
 */

package CarLot;

import java.math.BigDecimal;

public class Car
{
    private String vin, make, model, color;
    private BigDecimal price;
    private long odometerMiles;
    private CarKey key;
    
    public String getVin()
    {
        return vin;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public BigDecimal getPrice()
    {
        return price;
    }
    
    public long getOdometerMiles()
    {
        return odometerMiles;
    }
    
    public void setVin(String vin)
    {
        this.vin = vin;
    }
    
    public void setMake(String make)
    {
        this.make = make;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public void setColor(String color)
    {
        this.color = color;
    }
    
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    public void setOdometerMiles(long odometerMiles)
    {
        this.odometerMiles = odometerMiles;
    }
}