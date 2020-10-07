/**
 * SuperPerson - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

public class SuperPerson
{
    private int superPersonID;
    
    @NotBlank(message = "Superhero or supervillain name must not be blank.")
    @Size(max = 60, message = "Superhero or supervillain name must be less than 60 characters.")
    private String superPersonName;
    
    @NotBlank(message = "Superhero or supervillain description must not be blank.")
    @Size(max = 200, message = "Superhero or supervillain description must be less than w00 characters.")
    private String superPersonDescription;

    private boolean isSuperHero;
    
    private SuperPower superPower;
    private List<Organizations> organizations;
    private List<Sightings> sightings;

    @Override
    public String toString() {
        return "SuperPerson{" + "superPersonID=" + superPersonID + ", superPersonName=" + superPersonName + ", superPersonDescription=" + superPersonDescription + ", isSuperHero=" + isSuperHero + ", superPower=" + superPower + ", organizations=" + organizations + ", sightings=" + sightings + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + this.superPersonID;
        hash = 79 * hash + Objects.hashCode(this.superPersonName);
        hash = 79 * hash + Objects.hashCode(this.superPersonDescription);
        hash = 79 * hash + (this.isSuperHero ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.superPower);
        hash = 79 * hash + Objects.hashCode(this.organizations);
        hash = 79 * hash + Objects.hashCode(this.sightings);
        
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
        
        final SuperPerson other = (SuperPerson) obj;
        
        if (this.superPersonID != other.superPersonID)
        {
            return false;
        }
        
        if (this.isSuperHero != other.isSuperHero)
        {
            return false;
        }
        
        if (!Objects.equals(this.superPersonName, other.superPersonName))
        {
            return false;
        }
        
        if (!Objects.equals(this.superPersonDescription, other.superPersonDescription))
        {
            return false;
        }
        
        if (!Objects.equals(this.superPower, other.superPower))
        {
            return false;
        }
        
        if (!Objects.equals(this.organizations, other.organizations))
        {
            return false;
        }
        
        if (!Objects.equals(this.sightings, other.sightings))
        {
            return false;
        }
        
        return true;
    }
    
    public int getSuperPersonID()
    {
        return superPersonID;
    }

    public void setSuperPersonID(int superPersonID)
    {
        this.superPersonID = superPersonID;
    }

    public String getSuperPersonName()
    {
        return superPersonName;
    }

    public void setSuperPersonName(String superPersonName)
    {
        this.superPersonName = superPersonName;
    }

    public String getSuperPersonDescription()
    {
        return superPersonDescription;
    }

    public void setSuperPersonDescription(String superPersonDescription)
    {
        this.superPersonDescription = superPersonDescription;
    }

    public boolean getIsSuperHero()
    {
        return isSuperHero;
    }

    public void setIsSuperHero(boolean isSuperHero)
    {
        this.isSuperHero = isSuperHero;
    }

    public SuperPower getSuperPower()
    {
        return superPower;
    }

    public void setSuperPower(SuperPower superPower)
    {
        this.superPower = superPower;
    }

    public List<Organizations> getOrganizations()
    {
        return organizations;
    }

    public void setOrganizations(List<Organizations> organizations)
    {
        this.organizations = organizations;
    }

    public List<Sightings> getSightings()
    {
        return sightings;
    }

    public void setSightings(List<Sightings> sightings)
    {
        this.sightings = sightings;
    }
}