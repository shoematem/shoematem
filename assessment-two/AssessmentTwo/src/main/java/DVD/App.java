/**
 * App - This is the main class to the program, DVD. This is where the program starts.
 * --------------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/18/2020
 */

package DVD;

import DVD.Controller.DVDController;
import DVD.DAO.DVDDao;
import DVD.DAO.DVDDaoFileImpl;
import DVD.UI.DVDView;
import DVD.UI.UserIO;
import DVD.UI.UserIOConsoleImpl;

public class App
{
    /**
     * main - where the entire program starts
     * @param args - not used
     */
    public static void main(String[] args)
    {
        UserIO myIO = new UserIOConsoleImpl();
        DVDView myView = new DVDView(myIO);
        DVDDao myDao = new DVDDaoFileImpl();
        DVDController controller = new DVDController(myDao, myView);
        
        controller.run();
    }
}