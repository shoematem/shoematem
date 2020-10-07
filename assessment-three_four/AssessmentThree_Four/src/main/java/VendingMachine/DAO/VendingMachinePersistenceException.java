/**
 * VendingMachinePersistenceException - this throws an exception for the file
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */

package VendingMachine.DAO;

public class VendingMachinePersistenceException extends Exception
{
    public VendingMachinePersistenceException(String message)
    {
        super(message);
    }
    
    public VendingMachinePersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}