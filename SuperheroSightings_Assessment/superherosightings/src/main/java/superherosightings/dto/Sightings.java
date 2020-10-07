/**
 * Sightings - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

public class Sightings
{
    private int sightingID;
    private Location location;
    private List<SuperPerson> superPerson;
    
    @NotBlank(message = "Sighting date must not be blank.")
    private String sightingDate;

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + this.sightingID;
        hash = 89 * hash + Objects.hashCode(this.sightingDate);
        hash = 89 * hash + Objects.hashCode(this.location);
        hash = 89 * hash + Objects.hashCode(this.superPerson);
        
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
        
        final Sightings other = (Sightings) obj;
        
        if (this.sightingID != other.sightingID)
        {
            return false;
        }
        
        if (!Objects.equals(this.sightingDate, other.sightingDate))
        {
            return false;
        }
        
        if (!Objects.equals(this.location, other.location))
        {
            return false;
        }
        
        if(!Objects.equals(this.superPerson, other.superPerson))
        {
            return false;
        }
        
        return true;
    }

    public int getSightingID()
    {
        return sightingID;
    }

    public void setSightingID(int sightingID)
    {
        this.sightingID = sightingID;
    }

    public String getSightingDate()
    {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate)
    {
        this.sightingDate = sightingDate;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }
    
    public List<SuperPerson> getSuperPerson()
    {
        return superPerson;
    }
    
    public void setSuperPerson(List<SuperPerson> superPerson)
    {
        this.superPerson = superPerson;
    }
}