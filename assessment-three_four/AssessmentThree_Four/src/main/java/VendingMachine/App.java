/**
 * App - this is the start of the program. It uses dependency injection to follow
 * the MVC design pattern
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine;

import VendingMachine.Controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.RunApp();
    }
}