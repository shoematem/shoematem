/**
 * @author Matthew Shoemate
 */

package ClassRoster.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao
{
    public static final String AUDIT_FILE = "audit.txt";
    
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException
    {
        PrintWriter out;
        
        try
        {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e)
        {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }
}