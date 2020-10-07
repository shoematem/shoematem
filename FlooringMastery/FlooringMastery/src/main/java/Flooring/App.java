/**
 * App - the start of the program. It uses Spring DI for dependency injection
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring;

import Flooring.Controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("controller", FlooringController.class);
        controller.RunApp(); //make each method camel case "runApp"
    }
}