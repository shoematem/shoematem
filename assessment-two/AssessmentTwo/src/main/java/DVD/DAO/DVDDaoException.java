/**
 * DVDDaoException - This class is used to throw any exceptions throughout the program.
 * It has two constructors to be used throughout the program. It extends the class Exception.
 * -----------------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/18/2020
 */

package DVD.DAO;

public class DVDDaoException extends Exception
{
    public DVDDaoException(String message)
    {
        super(message);
    }
    
    public DVDDaoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}