/**
 * @author Matthew Shoemate
 */

package ClassRoster.Service;

public class ClassRosterDuplicateIDException extends Exception
{
    public ClassRosterDuplicateIDException(String message)
    {
        super(message);
    }
    
    public ClassRosterDuplicateIDException(String message, Throwable cause)
    {
        super(message, cause);
    }
}