/**
 * VendingMachineDao
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.DAO;

import VendingMachine.DTO.VendingMachine;
import java.util.Map;

public interface VendingMachineDao
{
    Map<String, VendingMachine> GetInventoryList() throws VendingMachinePersistenceException;
    void WriteToFile() throws VendingMachinePersistenceException;
}