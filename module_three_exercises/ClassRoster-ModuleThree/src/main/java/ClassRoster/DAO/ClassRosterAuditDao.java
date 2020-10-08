/**
 * @author Matthew Shoemate
 */

package ClassRoster.DAO;

public interface ClassRosterAuditDao
{
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}