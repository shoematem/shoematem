/**
 * @author Matthew Shoemate
 */

package DVD.UI;

import DVD.DAO.DVDDao;
import DVD.DAO.DVDDaoException;
import DVD.DAO.DVDDaoFileImpl;
import DVD.DTO.DVD;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DVDView
{
    private final UserIO myIO;
    
    public DVDView(UserIO myIO)
    {
        this.myIO = myIO;
    }
    
    public int PrintMenuGetSelection()
    {
        myIO.print("Main Menu");
        myIO.print("1. Add DVD");
        myIO.print("2. Remove DVD");
        myIO.print("3. Edit DVD from Collection");
        myIO.print("4. List DVDs");
        myIO.print("5. Display DVD Information");
        myIO.print("6. Search DVD by Title");
        myIO.print("7. Exit");
        
        return myIO.readInt("Please select from the above choices.", 1, 7);
    }
    
    public DVD getNewDVDInfo()
    {
        String dvdName = myIO.readString("Please enter a DVD title.");
        String dvdDate = myIO.readString("Please enter a DVD date (mm/dd/yyyy).");
        String dvdRating = myIO.readString("Please enter a MPAA rating.");
        String dvdDirectorName = myIO.readString("Please enter the DVD's director name.");
        String dvdStudio = myIO.readString("Please enter the studio the DVD was made in.");
        String userRating = myIO.readString("Please enter a verbal rating you would like to give.");
        
        DVD currentDVD = new DVD(dvdName);
        currentDVD.setDVDDate(dvdDate);
        currentDVD.setDVDDirectorName(dvdDirectorName);
        currentDVD.setDVDStudio(dvdStudio);
        currentDVD.setUserRating(userRating);
        currentDVD.setDVDRating(dvdRating);
        
        return currentDVD;
    }
    
    public void DisplayDVDList(List<DVD> dvdList)
    {
        myIO.print("");
        
        for(DVD currentDVD : dvdList)
        {
            String dvdInfo = currentDVD.getDVDName();
            myIO.print(dvdInfo);
        }
        
        myIO.readString("Please hit enter to continue.");
    }
    
    public void DisplayDVD(DVD dvd)
    {
        if(dvd != null)
        {            
            String dvdInfo = "\n" + dvd.getDVDName();
            dvdInfo += "\nDVD date: " + dvd.getDVDDate();
            dvdInfo += "\nDVD rating: " + dvd.getDVDRating();
            dvdInfo += "\nDVD director name: " + dvd.getDVDDirectorName();
            dvdInfo += "\nDVD studio name: " + dvd.getDVDStudio();
            dvdInfo += "\nUser rating: " + dvd.getUserRating();
            
            myIO.print(dvdInfo);
        }
        
        myIO.readString("Please hit enter to continue.");
    }
    
    public void SearchDVD(String dvdName, Map<String, DVD> dvdList) throws DVDDaoException
    {   
        boolean found = false;
        DVDDao dao = new DVDDaoFileImpl();

        for(String dvdKey : dvdList.keySet())
        {
            if(dvdKey.contains(dvdName))
            {
                found = true;
                DVD getDVDInfo = dao.DisplayDVDInfo(dvdKey);
                
                String dvdInfo = "\n" + getDVDInfo.getDVDName();
                myIO.print(dvdInfo);
            }
        }
        
        if(!found)
        {
            myIO.print("No such DVD.");
        }
    }
    
    public void DisplayRemoveDVD(DVD dvdName) throws DVDDaoException
    {   
        if(dvdName != null)
        {
            myIO.print("DVD successfully removed.");
        }
        
        myIO.readString("Please hit enter to continue.");
    }
    
    public DVD EditDVD(String dvdName)
    {
        String dvdDate = myIO.readString("Please enter a DVD date (mm/dd/yyyy).");
        String dvdRating = myIO.readString("Please enter a MPAA rating.");
        String dvdDirectorName = myIO.readString("Please enter the DVD's director name.");
        String dvdStudio = myIO.readString("Please enter the studio the DVD was made in.");
        String userRating = myIO.readString("Please enter a verbal rating you would like to give.");
        
        DVD currentDVD = new DVD(dvdName);
        currentDVD.setDVDDate(dvdDate);
        currentDVD.setDVDDirectorName(dvdDirectorName);
        currentDVD.setDVDStudio(dvdStudio);
        currentDVD.setUserRating(userRating);
        currentDVD.setDVDRating(dvdRating);
        
        return currentDVD;
    }
    
    public String GoBackMainMenu(String choiceType)
    {
        boolean goodInput = false;
        String userDecision = "";
        String chooseFrom = "Would you like to go back to the main menu or would you like the program to search for possible matches? Please type in 'main menu' or '" + choiceType + "'.";
   
        myIO.print("\nThere is no DVD matching that name.");
        
        do
        {
            userDecision = myIO.readString(chooseFrom);
            
            if(!userDecision.equals(choiceType) && !userDecision.equals("main menu"))
            {
                myIO.print("\nYou did not enter 'main menu' or '" + choiceType + "'.");
            } else
            {                
                goodInput = true;
            }
        } while(!goodInput);
            
        return userDecision;
    }
    
    public String getDVDChoice()
    {
        return myIO.readString("Please enter the DVD name.");
    }
    
    public void DisplayCreateDVDBanner()
    {
        myIO.print("\n--- Create DVD ---");
    }
    
    public void DisplayDVDListBanner()
    {
        myIO.print("\n--- List of DVDs ---");
    }
    
    public void DisplayEditDVDBanner()
    {
        myIO.print("\n--- Edit DVDs ---");
    }
    
    public void DisplayDVDInfoBanner()
    {
        myIO.print("\n--- DVD Information ---");
    }
    
    public void DisplaySearchDVDBanner()
    {
        myIO.print("\n--- Search DVD by Title ---");
    }
    
    public void DisplayRemoveDVDBanner()
    {
        myIO.print("\n--- Remove DVD ---");
    }
    
    public void DisplayErrorMessage(String errorMsg)
    {
        myIO.print("\n--- ERROR ---");
        myIO.print(errorMsg);
    }
    
    public void DisplayUnknownCommand()
    {
        myIO.print("\nUnknown Command!");
    }
    
    public void DisplayCreateSuccessBanner()
    {
        myIO.readString("\nDVD successfully created. Please hit enter to continue.");
    }
    
    public void DisplayEditSuccessBanner()
    {
        myIO.readString("\nDVD successfully created. Please hit enter to continue.");
    }
}