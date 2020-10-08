/**
 * Organizations - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

public class Organizations
{
    private int organizationID;
    
    @NotBlank(message = "Organization name must not be blank.")
    @Size(max = 100, message = "Organizaiton name must be less than 100 characters.")
    private String organizationName;
    
    @NotBlank(message = "Organization description must not be blank.")
    @Size(max = 200, message = "Organization description must be less than 200 characters.")
    private String organizationDescription;
    
    @NotBlank(message = "Organization phone number must not be blank.")
    @Size(max = 200, message = "Contact information must be less than 200 characters.")
    private String contactInfo;
    
    private Location location;
    private List<SuperPerson> superPerson;
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + this.organizationID;
        hash = 29 * hash + Objects.hashCode(this.organizationName);
        hash = 29 * hash + Objects.hashCode(this.organizationDescription);
        hash = 29 * hash + Objects.hashCode(this.contactInfo);
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.superPerson);
        
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
        
        final Organizations other = (Organizations) obj;
        
        if (this.organizationID != other.organizationID)
        {
            return false;
        }
        
        if (!Objects.equals(this.organizationName, other.organizationName))
        {
            return false;
        }
        
        if (!Objects.equals(this.organizationDescription, other.organizationDescription))
        {
            return false;
        }
        
        if (!Objects.equals(this.contactInfo, other.contactInfo))
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

    public int getOrganizationID()
    {
        return organizationID;
    }

    public void setOrganizationID(int organizationID)
    {
        this.organizationID = organizationID;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription()
    {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription)
    {
        this.organizationDescription = organizationDescription;
    }

    public String getContactInfo()
    {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo)
    {
        this.contactInfo = contactInfo;
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