/**
 * VendingMachineDataValidationException - exception class for data validation
 * --------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.Service;

public class VendingMachineDataValidationException extends Exception
{
    public VendingMachineDataValidationException(String message)
    {
        super(message);
    }
    
    public VendingMachineDataValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}