/**
 * VendingMachineAuditDaoFileImpl - implements its base class VendingMachineAuditDao.
 * It overrides its only method, WriteAuditEntry, to write to the audit.txt file
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */

package VendingMachine.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao
{
    public static final String AUDIT_FILE = "files/main/audit.txt";
    
    @Override
    public void WriteAuditEntry(String entry) throws VendingMachinePersistenceException
    {
        PrintWriter out; //declare variable to PrintWriter
        
        try
        {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e)
        {
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }
}