/**
 * @author Matthew Shoemate
 */

package ClassRoster.Service;

import ClassRoster.DAO.ClassRosterAuditDao;
import ClassRoster.DAO.ClassRosterPersistenceException;

public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao
{
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException
    {
        //do nothing...
    }
}