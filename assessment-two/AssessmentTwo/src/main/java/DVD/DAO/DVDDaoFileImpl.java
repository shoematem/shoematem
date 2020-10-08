/**
 * DVDDaoFile - This class handles the file input and output from the files given.
 * It uses several overrides to different methods. 
 * --------------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/18/2020
 */

package DVD.DAO;

import DVD.DTO.DVD;
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

public class DVDDaoFileImpl implements DVDDao
{
    private final Map<String, DVD> dvds = new HashMap<>();
    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";
    
     /**
     * WriteLibrary - Writes all DVDs in the library out to a ROSTER_FILE. See LoadLibrary for file format.
     * ---------------------------------------------------------------------------
     * @throws ClassRosterDaoException 
     */
    private void WriteLibrary() throws DVDDaoException
    {
        PrintWriter out;
        
        try
        {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch(IOException e)
        {
            throw new DVDDaoException("Could not save DVD data.", e);
        }
        
        //Write out the DVDs to the library
        String dvdAsText;
        int listSize = 0;
        List<DVD> dvdList = new ArrayList<>(dvds.values());
        
        for(DVD currentDVD : dvdList)
        {
            //initialize listSize to the dvdList size
            listSize = dvdList.size();
            //turn a DVD into String
            dvdAsText = MarshallDVD(currentDVD, listSize);
            //write the DVD object to the file
            out.println(dvdAsText);
            //force PrintWriter to write the line to the file
            out.flush();
        }
        
        out.close(); //clean up
    }
    
    /**
     * MarshallDVD - turns the DVD object into a line of text so we can write to the file
     * ----------------------------------------------------------------------------------
     * @param dvd - DVD object
     * @param listSize - how many lines are in the file, currently
     * @return dvdAsText - the String of text that is going to be written to the file
     */
    private String MarshallDVD(DVD dvd, int listSize)
    {
        String dvdAsText = Integer.toString(listSize + 1) + DELIMITER;
        dvdAsText += dvd.getDVDName() + DELIMITER;
        dvdAsText += dvd.getDVDDate() + DELIMITER;
        dvdAsText += dvd.getDVDRating() + DELIMITER;
        dvdAsText += dvd.getDVDDirectorName() + DELIMITER;
        dvdAsText += dvd.getDVDStudio() + DELIMITER;
        dvdAsText += dvd.getUserRating();
        
        return dvdAsText;
    }
    
    /**
     * UnmarshallDVD - reads each line from the file and splits it by the given delimiter.
     * Which then leaves us with an array of strings that is stored in dvdTokens
     * ------------------------------------------------------------------------------------
     * @param dvdAsText - the String of text read from the file
     * @return  dvdFromFile - the DVD just read from the file
     */
    private DVD UnmarshallDVD(String dvdAsText)
    {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdName = dvdTokens[1];
        
        DVD dvdFromFile = new DVD(dvdName);
        dvdFromFile.setDVDIndex(Integer.parseInt(dvdTokens[0]));
        dvdFromFile.setDVDDate(dvdTokens[2]);
        dvdFromFile.setDVDRating(dvdTokens[3]);
        dvdFromFile.setDVDDirectorName(dvdTokens[4]);
        dvdFromFile.setDVDStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        
        return dvdFromFile;
    }
    
    /**
     * LoadLibrary - 
     * ----------------------------------------------------------------------------
     * @throws DVDDaoException 
     */
    private void LoadLibrary() throws DVDDaoException
    {
        Scanner scanner;
        
        try
        {
            //create a Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch(FileNotFoundException e)
        {
            throw new DVDDaoException("Could not load DVD library into memory.", e);
        }
        
        String currentLine; //currentLine holds the most recent line read from the file
        DVD currentDVD; //currentStudent holds the most recent student unmarshalled
        
        //Go through ROSTER_FILE line by line, decoding each line into a DVD object by calling the UnmarshallDVD method.
        //Process while we have more lines in the file
        while(scanner.hasNextLine())
        {
            currentLine = scanner.nextLine(); //get the next line in the file
            currentDVD = UnmarshallDVD(currentLine); //unmarshall the line into a DVD
            dvds.put(currentDVD.getDVDName(), currentDVD);
        }
        
        scanner.close(); //close scanner
    }
    
    /**
     * CreateDVD
     * ----------------------------------------------------------------------------
     * @param dvdName
     * @param dvd
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public DVD CreateDVD(String dvdName, DVD dvd) throws DVDDaoException
    {
        LoadLibrary();
        DVD newDVD = dvds.put(dvdName, dvd);
        WriteLibrary();
        
        return newDVD;
    }

    /**
     * ListAllDVDs
     * -----------------------------------------------------------------------------
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public List<DVD> ListAllDVDs() throws DVDDaoException
    {
        LoadLibrary();
        
        return new ArrayList<>(dvds.values());
    }

    /**
     * EditDVD
     * -----------------------------------------------------------------------------
     * @param dvdName
     * @param dvd
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public DVD EditDVD(String dvdName, DVD dvd) throws DVDDaoException
    {
        LoadLibrary();
        DVD editDVD = dvds.put(dvdName, dvd);
        WriteLibrary();
        
        return editDVD;
    }

    /**
     * RemoveDVD
     * ----------------------------------------------------------------------------
     * @param dvdName
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public DVD RemoveDVD(String dvdName) throws DVDDaoException
    {
        LoadLibrary();
        DVD removedDVD = dvds.remove(dvdName);
        WriteLibrary();
        
        return removedDVD;
    }
    
    /**
     * DisplayDVDInfo
     * -----------------------------------------------------------------------------
     * @param dvdName
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public DVD DisplayDVDInfo(String dvdName) throws DVDDaoException
    {
        LoadLibrary();
        
        return dvds.get(dvdName);
    }
    
    /**
     * SearchDVDs
     * ----------------------------------------------------------------------------
     * @param dvdName
     * @return
     * @throws DVDDaoException 
     */
    @Override
    public Map<String, DVD> SearchDVDs(String dvdName) throws DVDDaoException
    {
        Map<String, DVD> searchDVD = new HashMap<>();
        LoadLibrary();
        
        for(String dvdKey : dvds.keySet())
        {
            searchDVD.put(dvdKey, dvds.get(dvdKey));
        }

        return searchDVD;
    }
}