/**
 * DVDDao - A public interface that directs what the user chose. It's the base class
 * of DVDDaoFileImpl. Each function throws DVDDaoException.
 * ------------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/18/2020
 */

package DVD.DAO;

import DVD.DTO.DVD;
import java.util.List;
import java.util.Map;

public interface DVDDao
{
     /** 
     * Adds the given DVD to the library and associates it with the given DVD name.If there is already a DVD
     * associated with the given DVD name it will return that DVD object, otherwise it will return null.
     * 
     * @param dvdName - name ID with which DVD is to be associated
     * @param dvd - DVD info to be added to the library
     * @return the DID object previously associated with the given DVD name if it exists, null otherwise
     * @throws DVDDaoException
     */
    DVD CreateDVD(String dvdName, DVD dvd) throws DVDDaoException;
    
     /**
     * Returns a List of all DVDs in the library.
     * 
     * @return List containing all DVDs in the library.
     * @throws DVDDaoException
     */
    List<DVD> ListAllDVDs() throws DVDDaoException;
    
     /**
     * Returns the DVD object associated with the given DVD name. Returns null if no such DVD exists
     * 
     * @param dvdName - DVD name of the DVD to retrieve
     * @param dvd - DVD info to be edited to the library
     * @return the DVD object associated with the given DVD name, null if no such DVD exists
     * @throws DVDDaoException
     */
    DVD EditDVD(String dvdName, DVD dvd) throws DVDDaoException;
    
    /**
     * Removes from the library the DVD associated with the DVD name. Returns the DVD object that is
     * being removed or null if there is no DVD associated with the given DVD name
     * 
     * @param dvdName - DVD name of the DVD to be removed
     * @return DVD object that was removed or null if no DVD was associated with the given DVD name
     * @throws DVD.DAO.DVDDaoException
     */
    DVD RemoveDVD(String dvdName) throws DVDDaoException;
    
    /**
     * Returns the DVD information of the DVD associated with the given DVD name. Returns the DVD object
     * that is being removed or null if there is no DVD associated with the given DVD name
     * @param dvdName - DVD name of the DVD to be removed
     * @return DVD object that was given or null if no DVD was associated with the given DVD name
     * @throws DVDDaoException 
     */
    DVD DisplayDVDInfo(String dvdName) throws DVDDaoException;
    
    /**
     * Returns the DVD names of the DVD name associated with the given search parameters. Returns the DVD
     * names that was searched for or null if there is no DVD associated with the given DVD name
     * @param dvdName - DVD name of the DVD to be removed
     * @return List containing all DVDs that were searched
     * @throws DVDDaoException 
     */
    Map<String, DVD> SearchDVDs(String dvdName) throws DVDDaoException;
}