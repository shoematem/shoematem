/**
 * FlooringMasteryCalculations - does all of the calculations for the material cost,
 * labor cost, tax, and total for each order that is created or edited. It uses
 * BigDecimal for accurate calculations
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FlooringCalculations
{
    private BigDecimal materialCost, laborCost, tax, total;
    
    /**
     * CalcMaterialCost - this method calculates the material cost
     * -------------------------------------------------------------------------
     * @param area - given from the user
     * @param costPerSqFoot - given from the products file for the specific
     * product type the user wants
     * @return - the calculated material cost
     */
    public String CalcMaterialCost(String area, String costPerSqFoot)
    {
        BigDecimal convArea = new BigDecimal(area);
        BigDecimal convCostPerSqFoot = new BigDecimal(costPerSqFoot);
        
        convArea = convArea.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        convCostPerSqFoot = convCostPerSqFoot.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        
        this.materialCost = convArea.multiply(convCostPerSqFoot); //multiplies the area by cost per square foot
        
        return this.materialCost.setScale(2, RoundingMode.HALF_UP).toPlainString(); //returns a string without any notation
    }
    
    /**
     * CalcLaborCost - this method calculates the labor cost
     * -------------------------------------------------------------------------
     * @param area - given from the user
     * @param laborCostPerSqFoot - given from the products file for the specific
     * product type the user wants
     * @return - the calculated labor cost
     */
    public String CalcLaborCost(String area, String laborCostPerSqFoot)
    {
        BigDecimal convArea = new BigDecimal(area);
        BigDecimal convLaborCostPerSqFoot = new BigDecimal(laborCostPerSqFoot);
        
        convArea = convArea.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        convLaborCostPerSqFoot = convLaborCostPerSqFoot.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        
        this.laborCost = convArea.multiply(convLaborCostPerSqFoot); //multiples the area by labor cost per square foot
        
        return this.laborCost.setScale(2, RoundingMode.HALF_UP).toPlainString(); //returns a string without any notation
    }
    
    /**
     * CalcTax - this method calculates the tax
     * -------------------------------------------------------------------------
     * @param taxRate - given from the StateTaxes file for the specific state
     * the user wants
     * @return - the calculated tax
     */
    public String CalcTax(String taxRate)
    {
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal convTaxRate = new BigDecimal(taxRate);
        
        oneHundred = oneHundred.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        convTaxRate = convTaxRate.setScale(2, RoundingMode.HALF_UP); //sets it to 2 right decimals
        
        convTaxRate = convTaxRate.divide(oneHundred); //converts the tax rate to decimal
        this.tax = this.materialCost.add(this.laborCost); //adds the material cost and the labor cost
        this.tax = this.tax.multiply(convTaxRate); //multiples the tax by the tax rate
        
        return this.tax.setScale(2, RoundingMode.HALF_UP).toPlainString(); //returns a string without any notation
    }
    
    /**
     * CalcTotal - calculates the total
     * -------------------------------------------------------------------------
     * @return - laborCost + materialCost + tax
     */
    public String CalcTotal()
    {
        this.total = this.laborCost.add(this.materialCost.add(this.tax));
        
        return this.total.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
    
    public void setMaterialCost(BigDecimal materialCost)
    {
        this.materialCost = materialCost;
    }
    
    public void setLaborCost(BigDecimal laborCost)
    {
        this.laborCost = laborCost;
    }
    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }
            
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }
    
    public BigDecimal getMaterialCost()
    {
        return materialCost;
    }

    public BigDecimal getLaborCost()
    {
        return laborCost;
    }

    public BigDecimal getTax()
    {
        return tax;
    }

    public BigDecimal getTotal()
    {
        return total;
    }
}