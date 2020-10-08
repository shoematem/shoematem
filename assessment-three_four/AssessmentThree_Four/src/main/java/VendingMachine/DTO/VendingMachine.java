/**
 * VendingMachine - this class is the setter and getter fields from the files. It
 * uses a constructor and defaults the toString method if we were to use it for the
 * file being read from
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.DTO;

import java.util.Objects;

public class VendingMachine
{
    private int numInventory;
    private String drinkName, drinkType, drinkPrice;
    
    public VendingMachine(String drinkName)
    {
        this.drinkName = drinkName;
    }

    @Override
    public String toString()
    {
        return "{" + "" + drinkName
                + "/" + drinkType
                + "/" + drinkPrice
                + "/" + numInventory + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        
        hash = 97 * hash + Objects.hashCode(this.drinkName);
        hash = 97 * hash + Objects.hashCode(this.drinkType);
        hash = 97 * hash + Objects.hashCode(this.numInventory);
        hash = 97 * hash + Objects.hashCode(this.drinkPrice);
        
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
        
        final VendingMachine other = (VendingMachine) obj;
        
        if (!Objects.equals(this.drinkName, other.drinkName))
        {
            return false;
        }
        
        if (!Objects.equals(this.drinkType, other.drinkType))
        {
            return false;
        }
        
        if (!Objects.equals(this.numInventory, other.numInventory))
        {
            return false;
        }
        
        if (!Objects.equals(this.drinkPrice, other.drinkPrice))
        {
            return false;
        }
        
        return true;
    }

    public String getDrinkName()
    {
        return drinkName;
    }

    public void setDrinkName(String drinkName)
    {
        this.drinkName = drinkName;
    }

    public String getDrinkType()
    {
        return drinkType;
    }

    public void setDrinkType(String drinkType)
    {
        this.drinkType = drinkType;
    }

    public int getNumInventory()
    {
        return numInventory;
    }

    public void setNumInventory(int numInventory)
    {
        this.numInventory = numInventory;
    }

    public String getDrinkPrice()
    {
        return drinkPrice;
    }

    public void setDrinkPrice(String drinkPrice)
    {
        this.drinkPrice = drinkPrice;
    }
}