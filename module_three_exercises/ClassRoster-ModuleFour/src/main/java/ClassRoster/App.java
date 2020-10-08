//@author Matthew Shoemate

package ClassRoster;

import ClassRoster.Controller.ClassRosterController;
//import ClassRoster.DAO.ClassRosterAuditDao;
//import ClassRoster.DAO.ClassRosterAuditDaoFileImpl;
//import ClassRoster.DAO.ClassRosterDao;
//import ClassRoster.DAO.ClassRosterDaoFileImpl;
//import ClassRoster.Service.ClassRosterServiceLayer;
//import ClassRoster.Service.ClassRosterServiceLayerImpl;
//import ClassRoster.UI.ClassRosterView;
//import ClassRoster.UI.UserIO;
//import ClassRoster.UI.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
        /*UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
        
        controller.run();*/
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}