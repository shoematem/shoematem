/**
 * Orders - this initializes the variables with getters and setters
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/202
 */

package Flooring.DTO;

public class Orders
{
    private int orderID;
    private String customerName, productType, state, area, costPerSqFoot,
            laborCost, laborCostPerSqFoot, materialCost, stateTaxRate, calcTax, total;
    
    /**
     * Orders - constructor that sets the order ID to be used throughout
     * -------------------------------------------------------------------------
     * @param orderID - the order ID coming in from the user
     */
    public Orders(int orderID)
    {
        this.orderID = orderID;
    }
    
    /**
     * toString - overrides the toString() method from java. It formats the string
     * with all of the variables of this class so it can be used throughout the
     * program
     * -------------------------------------------------------------------------
     * @return - the formatted string containing each and every variable
     */
    @Override
    public String toString()
    {
        return orderID + "/" + customerName + "/" + productType
                + "/" + state + "/" + area + "/" + costPerSqFoot
                + "/" + laborCost + "/" + laborCostPerSqFoot
                + "/" + materialCost + "/" + stateTaxRate
                + "/" + calcTax + "/" + total;
    }
    
    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }
    
    public void setArea(String area)
    {
        this.area = area;
    }
    
    public void setCostPerSqFoot(String costPerSqFoot)
    {
        this.costPerSqFoot = costPerSqFoot;
    }
    
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
        
    public void setLaborCost(String laborCost)
    {
        this.laborCost = laborCost;
    }
    
    public void setLaborCostPerSqFoot(String laborCostPerSqFoot)
    {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }
                
    public void setMaterialCost(String materialCost)
    {
        this.materialCost = materialCost;
    }
    
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public void setStateTaxRate(String stateTaxRate)
    {
        this.stateTaxRate = stateTaxRate;
    }
    
    public void setCalcTax(String calcTax)
    {
        this.calcTax = calcTax;
    }
    
    public void setTotal(String total) 
    {
        this.total = total;
    }
    
    public int getOrderID()
    {
        return orderID;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public String getProductType()
    {
        return productType;
    }

    public String getState()
    {
        return state;
    }

    public String getArea()
    {
        return area;
    }

    public String getCostPerSqFoot()
    {
        return costPerSqFoot;
    }


    public String getLaborCost()
    {
        return laborCost;
    }

    public String getLaborCostPerSqFoot()
    {
        return laborCostPerSqFoot;
    }

    public String getMaterialCost()
    {
        return materialCost;
    }

    public String getStateTaxRate()
    {
        return stateTaxRate;
    }

    public String getCalcTax()
    {
        return calcTax;
    }
    
    public String getTotal()
    {
        return total;
    }
}