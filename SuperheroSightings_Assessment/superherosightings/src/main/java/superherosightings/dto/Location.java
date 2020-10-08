/**
 * Location - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dto;

import java.util.Objects;
import javax.validation.constraints.*;

public class Location
{
    private int locationID;
    
    @NotBlank(message = "Location name must not be blank.")
    @Size(max = 60, message = "Location name must be less than 60 characters.")
    private String locationName;
    
    @NotBlank(message = "Location description must not be blank.")
    @Size(max = 200, message = "Location description must be less than 200 characters.")
    private String locationDescription;
    
    @NotBlank(message = "Address must not be blank.")
    @Size(max = 50, message = "Address must be less than 100 characters.")
    private String address;
    
    @NotNull(message = "Latitude must not be blank.")
    @Digits(integer = 3, fraction = 6, message = "Latitude must have 3 numbers before the decimal and 6 numbers after the decimal.")
    private double latitude;
    
    @NotNull(message = "Longitude must not be blank.")
    @Digits(integer = 3, fraction = 6, message = "Longitude must have 3 numbers before the decimal and 6 numbers after the decimal.")
    private double longitude;
    
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + this.locationID;
        hash = 41 * hash + Objects.hashCode(this.locationName);
        hash = 41 * hash + Objects.hashCode(this.locationDescription);
        hash = 41 * hash + Objects.hashCode(this.address);
        hash = 41 * hash + Objects.hashCode(this.latitude);
        hash = 41 * hash + Objects.hashCode(this.longitude);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        
        if (obj == null)
        {
            return false;
        }
        
        if (getClass() != obj.getClass())
        {
            return false;
        }
        
        final Location other = (Location) obj;
        
        if (this.locationID != other.locationID)
        {
            return false;
        }
        
        if (!Objects.equals(this.locationName, other.locationName))
        {
            return false;
        }
        
        if (!Objects.equals(this.locationDescription, other.locationDescription))
        {
            return false;
        }
        
        if (!Objects.equals(this.address, other.address))
        {
            return false;
        }
        
        if (!Objects.equals(this.latitude, other.latitude))
        {
            return false;
        }
        
        if (!Objects.equals(this.longitude, other.longitude))
        {
            return false;
        }
        
        return true;
    }

    public int getLocationID()
    {
        return locationID;
    }

    public void setLocationID(int locationID)
    {
        this.locationID = locationID;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    public String getLocationDescription()
    {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription)
    {
        this.locationDescription = locationDescription;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}