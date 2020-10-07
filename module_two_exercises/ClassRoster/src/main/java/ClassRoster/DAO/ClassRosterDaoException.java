//@author Matthew Shoemate

package ClassRoster.DAO;

public class ClassRosterDaoException extends Exception
{
    public ClassRosterDaoException(String message)
    {
        super(message);
    }
    
    public ClassRosterDaoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}