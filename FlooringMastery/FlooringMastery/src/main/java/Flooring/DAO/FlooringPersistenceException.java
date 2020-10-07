/**
 * FlooringPersistenceException - exception class for the program
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.DAO;

public class FlooringPersistenceException extends Exception
{
    public FlooringPersistenceException(String message)
    {
        super(message);
    }
    
    public FlooringPersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}