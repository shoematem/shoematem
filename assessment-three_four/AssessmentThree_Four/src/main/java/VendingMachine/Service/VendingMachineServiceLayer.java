/**
 * VendingMachineServiceLayer
 * --------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.Service;

import VendingMachine.DAO.VendingMachinePersistenceException;
import VendingMachine.DTO.VendingMachine;
import java.util.List;
import java.util.Map;

public interface VendingMachineServiceLayer
{
    String GetUserMoney(String drinkPrice, String userMoney) throws VendingMachinePersistenceException;
    List<VendingMachine> ListVendingInventory() throws VendingMachinePersistenceException;
    Map<String, String> DisplayDrinksFromType(String drinkType) throws VendingMachinePersistenceException;
    void RemoveVendedItem(String drinkName) throws VendingMachinePersistenceException;
}