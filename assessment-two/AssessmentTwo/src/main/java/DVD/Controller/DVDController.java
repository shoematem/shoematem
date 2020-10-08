/**
 * DVDController - This is the controller of the program. It handles what the user inputs
 * from the main menu and points to what each variable/method needs to be called for each
 * associated menu selection. There is one constructor in this class.
 * -------------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/18/2020
 */

package DVD.Controller;

import DVD.DAO.DVDDao;
import DVD.DAO.DVDDaoException;
import DVD.DTO.DVD;
import DVD.UI.DVDView;
import java.util.List;
import java.util.Map;

public class DVDController
{
    private final DVDView view;
    private final DVDDao dao;
    
    public DVDController(DVDDao dao, DVDView view)
    {
        this.dao = dao;
        this.view = view;
    }
    
    /**
     * run - handles the user's menu selection. The user will keep in there until they
     * explicitly want to exit the program (chooses 7)
     */
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try
        {
            while(keepGoing)
            {
                menuSelection = getMenuSelection();
                
                switch(menuSelection)
                {
                    case 1:
                        CreateDVD();
                        break;
                    case 2:
                        RemoveDVD(1);
                        break;
                    case 3:
                        EditDVD(1);
                        break;
                    case 4:
                        ListDVDs();
                        break;
                    case 5:
                        DisplayDVDInfo(1);
                        break;
                    case 6:
                        SearchDVD("search", "");
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        UnknownCommand();
                        break;
                }
            }
        } catch(DVDDaoException e)
        {
            view.DisplayErrorMessage(e.getMessage());
        }
    }
    
    /**
     * getMenuSelection - prints the menu selection to the user
     * ----------------------------------------------------------------------------
     * @return view.PrintMenuGetSelection() - prints the menu selection
     */
    public int getMenuSelection()
    {
        return view.PrintMenuGetSelection();
    }
    
    /**
     * CreateDVD - Creates the DVD that the user input from selecting to add a DVD
     * -----------------------------------------------------------------------------
     * @throws DVDDaoException 
     */
    private void CreateDVD() throws DVDDaoException
    {
        view.DisplayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.CreateDVD(newDVD.getDVDName(), newDVD);
        view.DisplayCreateSuccessBanner();
    }
    
    /**
     * DisplayDVDInfo - Displays the DVD info to the user
     * ---------------------------------------------------------------------------
     * @param timeIn - this is for if the user did not input a good DVD name - program goes to the search method
     * @throws DVDDaoException 
     */
    private void DisplayDVDInfo(int timeIn) throws DVDDaoException
    {
        if(timeIn == 1)
        {
            view.DisplayDVDInfoBanner();
        }
        
        String dvdName = view.getDVDChoice();
        DVD dvd = dao.DisplayDVDInfo(dvdName);
        
        if(dvd != null)
        {
            view.DisplayDVD(dvd);
        } else
        {
            String userChoice = view.GoBackMainMenu("info");
            
            if(userChoice.equals("info"))
            {
                SearchDVD("info", dvdName);
                DisplayDVDInfo(2);
            }
        }
    }
    
    /**
     * EditDVD - Edits the DVD that the user wants to edit
     * ------------------------------------------------------------------------------
     * @param timeIn - this is for if the user did not input a good DVD name - program goes to the search method
     * @throws DVDDaoException 
     */
    private void EditDVD(int timeIn) throws DVDDaoException
    {
        if(timeIn == 1)
        {
            view.DisplayEditDVDBanner();
        }
        
        String dvdName = view.getDVDChoice();
        DVD dvd = dao.DisplayDVDInfo(dvdName);
        
        if(dvd != null)
        {
            DVD editDVD = view.EditDVD(dvdName);
            dao.EditDVD(dvdName, editDVD);
            view.DisplayEditSuccessBanner();
        } else
        {
            String userChoice = view.GoBackMainMenu("edit");
            
            if(userChoice.equals("edit"))
            {
                SearchDVD("edit", dvdName);
                EditDVD(2);
            }
        }
    }
    
    /**
     * ListDVDs - Lists the DVDs to the user
     * ----------------------------------------------------------------------------
     * @throws DVDDaoException 
     */
    private void ListDVDs() throws DVDDaoException
    {
        view.DisplayDVDListBanner();
        List<DVD> dvdList = dao.ListAllDVDs();
        view.DisplayDVDList(dvdList);
    }
    
    /**
     * RemoveDVD - Removes the DVD inputted to be removed
     * ----------------------------------------------------------------------------
     * @param timeIn - this is for if the user did not input a good DVD name - program goes to the search method
     * @throws DVDDaoException 
     */
    private void RemoveDVD(int timeIn) throws DVDDaoException
    {
        if(timeIn == 1)
        {
            view.DisplayRemoveDVDBanner();
        }
        
        String dvdName = view.getDVDChoice();
        DVD removeDVD = dao.RemoveDVD(dvdName);
        
        if(removeDVD != null)
        {
            view.DisplayRemoveDVD(removeDVD);
        } else
        {
            String userChoice = view.GoBackMainMenu("remove");
            
            if(userChoice.equals("remove"))
            {
                SearchDVD("remove", dvdName);
                RemoveDVD(2);
            }
        }
    }
    
    /**
     * SearchDVD - searches for DVDs that contain the input from the user
     * -----------------------------------------------------------------------------
     * @param choiceType - this is hard-coded in the program for what the menu selection was
     * @param dvdName - passes the DVD name that was inputted from the user
     * @throws DVDDaoException 
     */
    private void SearchDVD(String choiceType, String dvdName) throws DVDDaoException
    {
        if(choiceType.equals("search"))
        {
            view.DisplaySearchDVDBanner();
            dvdName = view.getDVDChoice();
        }
        
        Map<String, DVD> dvdList = dao.SearchDVDs(dvdName);
        view.SearchDVD(dvdName, dvdList);
    }
    
    /**
     * UnknownCommand - prints out that whatever the user input from the menu selection
     * is an unknown command
     */
    private void UnknownCommand()
    {
        view.DisplayUnknownCommand();
    }
}