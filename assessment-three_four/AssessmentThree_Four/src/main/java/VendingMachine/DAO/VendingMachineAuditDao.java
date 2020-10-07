/**
 * VendingMachineAuditDao - the interface for the audit text file
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */

package VendingMachine.DAO;

public interface VendingMachineAuditDao
{
    public void WriteAuditEntry(String entry) throws VendingMachinePersistenceException;
}