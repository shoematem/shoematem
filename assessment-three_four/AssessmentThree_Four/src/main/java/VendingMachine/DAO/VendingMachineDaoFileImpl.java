/**
 * VendingMachineDaoFileImpl - this implements its base class, VendingMachineDao.
 * It loads and writes the data. It uses data marshaling to extract and upload
 * the data
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.DAO;

import VendingMachine.DTO.VendingMachine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao
{
    public final String VENDINGMACHINE_FILE;
    public static final String DELIMITER = "::";
    public static final int NUMBER_OF_FIELDS = 4;
    
    private Map<String, VendingMachine> drinks = new HashMap<>();
    
    /**
     * VendingMachineDaoFileImpl - this constructor defaults the file if it is
     * not passed through
     */
    public VendingMachineDaoFileImpl()
    {
        VENDINGMACHINE_FILE = "files/main/vendingmachine.txt";
    }
    
    /**
     * VendingMachineDaoFileImpl - this constructor passes in a file name via the
     * given parameter
     * -------------------------------------------------------------------------
     * @param vendingMachineFile - sets the private field to the passed file
     */
    public VendingMachineDaoFileImpl(String vendingMachineFile)
    {
        VENDINGMACHINE_FILE = vendingMachineFile;
    }
    
    /**
     * MarshallVendingMachine - marshalls the data that needs to be written to the
     * file given. The example of the text file is Pepsi::SodaPop::1.89::3
     * which would be drink name::drink type::drink price::drink inventory
     * -------------------------------------------------------------------------
     * @param aVendingMachine
     * @return - the string of what the record is being written
     */
    private String MarshallVendingMachine(VendingMachine aVendingMachine)
    {
        String aVendingMachineText = aVendingMachine.getDrinkName() + DELIMITER;
        aVendingMachineText += aVendingMachine.getDrinkType() + DELIMITER;
        aVendingMachineText += aVendingMachine.getDrinkPrice() + DELIMITER;
        aVendingMachineText += aVendingMachine.getNumInventory();
        
        return aVendingMachineText;
    }
    
    /**
     * UnmarshallVendingMachine - unmarshalls the data that is being read from
     * the file given. This method grabs the length of the tokens delimited. It
     * then writes to the appropriate fields and sets them
     * -------------------------------------------------------------------------
     * @param vendingMachineAsText
     * @return - the complete record from the file
     */
    private VendingMachine UnmarshallVendingMachine(String vendingMachineAsText)
    {
        //split the string into 4 tokens
        String[] vendingMachineTokens = vendingMachineAsText.split(DELIMITER);
        
        if(vendingMachineTokens.length == NUMBER_OF_FIELDS)
        {
            String drinkName = vendingMachineTokens[0];
            
            VendingMachine vmFromFile = new VendingMachine(drinkName);
            vmFromFile.setDrinkType(vendingMachineTokens[1]);
            vmFromFile.setDrinkPrice(vendingMachineTokens[2]);
            vmFromFile.setNumInventory(Integer.parseInt(vendingMachineTokens[3]));
            
            return vmFromFile;
        } else
        {
            return null;
        }
    }
    
    /**
     * LoadVendingMachine - this loads the vending machine with all of the records
     * given from the file. It will load a scanner to read the record to the
     * machine for the user to read
     * -------------------------------------------------------------------------
     * @throws VendingMachinePersistenceException 
     */
    private void LoadVendingMachine() throws VendingMachinePersistenceException
    {
        String currentLine;
        VendingMachine currentDrink;
        Scanner scanner;
        
        try
        {
            //Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(VENDINGMACHINE_FILE))); //reads the file
        } catch(FileNotFoundException e)
        {
            throw new VendingMachinePersistenceException("Could not load vending machine data into memory.", e);
        }
        
        while(scanner.hasNextLine())
        {
            currentLine = scanner.nextLine();
            currentDrink = UnmarshallVendingMachine(currentLine);
            
            if(currentDrink != null)
            {
                drinks.put(currentDrink.getDrinkName(), currentDrink); //puts the drink name and a object array of currentDrink
            }
        }
       
        scanner.close();
    }
    
    /**
     * WriteVendingMachine - this writes any changes (removing a drink from the
     * inventory) to the file
     * -------------------------------------------------------------------------
     * @throws VendingMachinePersistenceException 
     */
    private void WriteVendingMachine() throws VendingMachinePersistenceException
    {
        PrintWriter out;
        
        try
        {
            out = new PrintWriter(new FileWriter(VENDINGMACHINE_FILE));
        } catch(IOException e)
        {
            throw new VendingMachinePersistenceException("Could not save DVD data.", e);
        }
        
        //Write out the Vending Machine to the library
        String vmAsText;

        List<VendingMachine> vmList = new ArrayList<>(drinks.values());
        
        for(VendingMachine currentDrink : vmList)
        {
            //turn a Vending Machine into String
            vmAsText = MarshallVendingMachine(currentDrink);
            //write the VendingMachine object to the file
            out.println(vmAsText);
            //force PrintWriter to write the line to the file
            out.flush();
        }
        
        out.close(); //clean up
    }
    
    /**
     * GetInventoryList - gets the entire list of drinks
     * -------------------------------------------------------------------------
     * @return the Map list of drinks
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public Map<String, VendingMachine> GetInventoryList() throws VendingMachinePersistenceException
    {
        LoadVendingMachine();
        
        return drinks;
    }
    
    /**
     * WriteToFile - this calls the method to write to the fail
     * -------------------------------------------------------------------------
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public void WriteToFile() throws VendingMachinePersistenceException
    {   
        WriteVendingMachine();
    }
}