/**
 * SuperPower - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 * @date 08/28/2020
 */

package superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

public class SuperPower
{
    private int superPowerID;
    
    @NotBlank(message = "Superpower name must not be blank.")
    @Size(max = 100, message = "Superpower name must be less than 100 characters.")
    private String superPowerName;
    
    private List<SuperPerson> superPerson;

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 11 * hash + this.superPowerID;
        hash = 11 * hash + Objects.hashCode(this.superPowerName);
        hash = 11 * hash + Objects.hashCode(this.superPerson);
        
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
        
        final SuperPower other = (SuperPower) obj;
        
        if (this.superPowerID != other.superPowerID)
        {
            return false;
        }
        
        if (!Objects.equals(this.superPowerName, other.superPowerName))
        {
            return false;
        }
        
        if(!Objects.equals(this.superPerson, other.superPerson))
        {
            return false;
        }
        
        return true;
    }

    public int getSuperPowerID()
    {
        return superPowerID;
    }

    public void setSuperPowerID(int superPowerID)
    {
        this.superPowerID = superPowerID;
    }

    public String getSuperPowerName()
    {
        return superPowerName;
    }

    public void setSuperPowerName(String superPowerName)
    {
        this.superPowerName = superPowerName;
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