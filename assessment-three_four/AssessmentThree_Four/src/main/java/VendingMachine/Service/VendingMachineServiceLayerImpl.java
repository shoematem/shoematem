/**
 * VendingMachineServiceLayerImpl - this class implements its base class
 * VendingMachineServiceLayer. It overrides each method given in the base class.
 * This handles the business calculations
 * --------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.Service;

import VendingMachine.DAO.VendingMachineAuditDao;
import VendingMachine.DAO.VendingMachineDao;
import VendingMachine.DAO.VendingMachinePersistenceException;
import VendingMachine.DTO.VendingChange;
import VendingMachine.DTO.VendingMachine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;
    
    /**
     * VendingMachineServiceLayerImpl - a constructor that passes two parameters
     * -------------------------------------------------------------------------
     * @param dao - passes the field of VendingMachineDao
     * @param auditDao - passes the field of VendingMachineAuditDao
     */
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    /**
     * DisplayDrinksFromType - this method displays the drinks the type that the
     * user chose. It brings in the drink type and grabs the inventory list from
     * the dao field. It will then loop through using a for loop to grab the
     * drinks for each drink type
     * -------------------------------------------------------------------------
     * @param drinkType
     * @return
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public Map<String, String> DisplayDrinksFromType(String drinkType) throws VendingMachinePersistenceException
    {
        String[] splitVMArr;
        String stringDrinkType;
        VendingMachine getDrinkType;
        
        Map<String, VendingMachine> vmArrList = dao.GetInventoryList();
        Map<String, String> vmList = new HashMap<>();
        
        for(String vmKey : vmArrList.keySet())
        {
            getDrinkType = vmArrList.get(vmKey); //get the key of the list
            stringDrinkType = getDrinkType.toString(); //convert the key to a string to be able to use easier
            
            if(stringDrinkType.contains(drinkType)) //checks if the drink type contains what the user input
            {
                splitVMArr = stringDrinkType.split("/"); //splits the string with a / (forward slash)
                vmList.put(vmKey, splitVMArr[2] + "/" + splitVMArr[3].replace("}", "")); //puts it into the declared HashMap
            }
        }
        
        return vmList;
    }
    
    /**
     * RemoveVendedItem - it removes the drink chosen from the user. It brings in
     * the drink list and then decrements the inventory. Once it is decremented
     * it creates a new instance of VendingMachine with the drinkName and sets
     * the variables and then writes to the VendingMachine and audit file
     * -------------------------------------------------------------------------
     * @param drinkName - passes in the drink name that needs to be removed
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public void RemoveVendedItem(String drinkName) throws VendingMachinePersistenceException
    {
        int drinkNumInventory = 0;
        String[] splitVMArr;
        String stringDrinkType;
        String drinkType = "", drinkPrice = "";
        VendingMachine getDrinkType, currentDrink;
        
        Map<String, VendingMachine> vmArrList = dao.GetInventoryList();
        
        for(String vmKey : vmArrList.keySet())
        {
            if(vmKey.contains(drinkName))
            {
                getDrinkType = vmArrList.get(vmKey);
                stringDrinkType = getDrinkType.toString();
                splitVMArr = stringDrinkType.split("/");
                drinkType = splitVMArr[1];
                drinkPrice = splitVMArr[2];
                drinkNumInventory = Integer.parseInt(splitVMArr[3].replaceAll("}", ""));
                drinkNumInventory--;
            }
        }
        
        currentDrink = new VendingMachine(drinkName);
        currentDrink.setDrinkType(drinkType);
        currentDrink.setDrinkPrice(drinkPrice);
        currentDrink.setNumInventory(drinkNumInventory);
        vmArrList.put(drinkName, currentDrink);
        
        dao.WriteToFile();
        auditDao.WriteAuditEntry("Drink " + drinkName + " REMOVED");
    }
    
    /**
     * ListVendingInventory - grabs the inventory list for the drinks and returns
     * the list to the user
     * -------------------------------------------------------------------------
     * @return - returns a list of the drink inventory to the user
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public List<VendingMachine> ListVendingInventory() throws VendingMachinePersistenceException
    {
        Map<String, VendingMachine> vmArrList = dao.GetInventoryList();
        
        return new ArrayList<>(vmArrList.values());
    }
    
    /**
     * GetUserMoney - this grabs the user's money and the drink price they chose.
     * It goes to calculate the change in the VendingChange class. It checks if
     * it holds a comma (,) to see if there's an array of coins coming back. If
     * not, then the difference between user money and drink price is negative
     * or they had exact change
     * -------------------------------------------------------------------------
     * @param drinkPrice - the drink price of the drink the user chose
     * @param userMoney - the money the user input into the vending machine
     * @return - returns the difference
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public String GetUserMoney(String drinkPrice, String userMoney) throws VendingMachinePersistenceException
    {
        String[] getChangeSpecs;
        VendingChange vendChange = new VendingChange(userMoney);
        String[] cashBack = new String[4];
        
        String difference = vendChange.CalcChange(drinkPrice);
        
        if(difference.contains(","))
        {
            getChangeSpecs = difference.split(",");
            
            if(!getChangeSpecs[0].equals(0))
            {
                cashBack[0] = getChangeSpecs[0];
            }
            
            if(!getChangeSpecs[1].equals(0))
            {
                cashBack[1] = getChangeSpecs[1];
            }
            
            if(!getChangeSpecs[2].equals(0))
            {
                cashBack[2] = getChangeSpecs[2];
            }
            
            if(!getChangeSpecs[3].equals(0))
            {
                cashBack[3] = getChangeSpecs[3];
            }
            
            difference = Arrays.toString(cashBack);
        }
        
        return difference;
    }
}