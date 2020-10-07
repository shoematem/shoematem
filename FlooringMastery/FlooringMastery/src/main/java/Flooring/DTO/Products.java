/**
 * Products - this initializes the variables inside this class with getters and
 * setters
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.DTO;

public class Products
{
    private final String productType;
    private String costPerSqFoot, laborCostPerSqFoot;
    
    /**
     * Products - constructor that sets the product type
     * -------------------------------------------------------------------------
     * @param productType - the product type entered from the user
     */
    public Products(String productType)
    {
        this.productType = productType;
    }
    
    /**
     * toString - this overrides the toString() method from Java. It formats the
     * string with all of the variables of this class so it can be used throughout
     * the program
     * -------------------------------------------------------------------------
     * @return - the formatted string containing each variable in the class
     */
    @Override
    public String toString()
    {
        return productType + "/" + costPerSqFoot + "/" + laborCostPerSqFoot;
    }

    public void setCostPerSqFoot(String costPerSqFoot)
    {
        this.costPerSqFoot = costPerSqFoot;
    }
   
    public void setLaborCostPerSqFoot(String laborCostPerSqFoot)
    {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    public String getCostPerSqFoot()
    {
        return costPerSqFoot;
    }

    public String getLaborCostPerSqFoot()
    {
        return laborCostPerSqFoot;
    }

    public String getProductType()
    {
        return productType;
    } 
}