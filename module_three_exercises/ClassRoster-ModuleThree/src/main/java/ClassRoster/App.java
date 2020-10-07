//@author Matthew Shoemate

package ClassRoster;

import ClassRoster.Controller.ClassRosterController;
import ClassRoster.DAO.ClassRosterDao;
import ClassRoster.DAO.ClassRosterDaoFileImpl;
import ClassRoster.Service.ClassRosterServiceLayer;
import ClassRoster.Service.ClassRosterServiceLayerImpl;
import ClassRoster.UI.ClassRosterView;
import ClassRoster.UI.UserIO;
import ClassRoster.UI.UserIOConsoleImpl;

public class App
{
    public static void main(String[] args)
    {
        UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
        
        controller.run();
    }
}