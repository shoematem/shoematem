/**
 * FlooringMasteryController - this is the controller of the program. It is the
 * overall backbone of the program. It has loops inside for user input and
 * directs the program on where to go for each function/method.
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */
package Flooring.Controller;

import Flooring.DAO.FlooringPersistenceException;
import Flooring.DTO.*;
import Flooring.Service.FlooringServiceLayer;
import Flooring.UI.FlooringView;
import java.util.HashMap;
import java.util.Map;

public class FlooringController
{
    private final FlooringView myView;
    private final FlooringServiceLayer myService;
    
    /**
     * FlooringController - constructor for the class. It sets the fields
     * myView and myService
     * -------------------------------------------------------------------------
     * @param myView - sets myView with this.myView
     * @param myService - sets myService with this.myService
     */
    public FlooringController(FlooringView myView, FlooringServiceLayer myService)
    {
        this.myView = myView;
        this.myService = myService;
    }
    
    /**
    * RunApp - the first loop of the program. It loops through the main menu
    * options and gets the user's selection.
    */
    public void RunApp()
    {
        boolean keepGoing = true;
        int menuSelection;
        
        try
        {
            do
            {
                menuSelection = myView.PrintMenuGetSelection(); //get the user's menu selection
                
                switch(menuSelection)
                {
                    case 1: //Display Orders
                        DisplayOrder();
                        break;
                    case 2: //Add an Order
                        AddOrder();
                        break;
                    case 3: //Edit an Order
                        EditOrder();
                        break;
                    case 4: //Remove an Order
                        RemoveOrder();
                        break;
                    case 5: //Export All Data
                        ExportData();
                        break;
                    case 6: //Quit Program
                        ExitMessage();
                        keepGoing = false;
                        break;
                    default:
                        UnknownCommand(menuSelection);
                }
            } while(keepGoing);
        } catch(FlooringPersistenceException e)
        {
            myView.DisplayErrorMessage(e.getMessage());
        }
    }
    
    /**
     * AddOrder - creates an order from what the user inputs
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void AddOrder() throws FlooringPersistenceException
    {
        Map<Integer, Orders> dummyMap = new HashMap<>();
        Map<String, String> products = myService.GetProducts(); //get a HashMap of the Products file
        Map<String, String> states = myService.GetStateTaxes(); //get a HashMap of the StateTaxes file
        
        myView.DisplayAddOrderBanner();
        
        String orderDate = myView.GetOrderDateInput("create");
        
        //we pass a dummy map to have an empty HashMap - null crashes the program
        //we pass 0 for order ID because it is impossible to have an order ID of
        //0. This will let the program know it is a completely new order and it is
        //not coming from the edit order method
        Orders order = myView.OrderInfo(0, dummyMap, products, states);
        order = myService.CalculateInputs(order); //calculate the user inputs
        myView.DisplayOrderSummary(order); //show the order summary to the user
        boolean doCreate = myView.GetConfirmationInput("create"); //get confirmation from the user to create the order
        
        if(doCreate)
        {
            myService.CreateOrder(order, orderDate);
            myView.DisplaySuccessfulBanner("Order successfully created");
        }
    }
    
    /**
     * DisplayOrder - displays a list of order IDs by order date back to the user
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void DisplayOrder() throws FlooringPersistenceException
    {
        myView.DisplayGetOrderBanner();
        
        String orderDate = myView.GetOrderDateInput("display");
        Map<Integer, Orders> order = myService.GetOrdersByDate(orderDate); //get a HashMap of orders by order date
        
        if(!order.isEmpty()) //if the HashMap is not empty
        {
            myView.DisplayOrderList(order);
        } else
        {
            myView.DisplayErrorMessage("There are no orders to display for that date.");
        }
        
        myView.DisplaySuccessfulBanner("");
    }
    
    /**
     * EditOrder - edits an order by an order ID. If the user did not input any
     * edits the program will exit this method and go back to the main menu
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void EditOrder() throws FlooringPersistenceException
    {
        Map<String, String> products = myService.GetProducts(); //get a HashMap of the Products file
        Map<String, String> states = myService.GetStateTaxes(); //get a HashMap of the StateTaxes file
        
        myView.DisplayEditOrderBanner();
        
        String orderDate = myView.GetOrderDateInput("edit");
        Map<Integer, Orders> order = myService.GetOrdersByDate(orderDate); //get a HashMap of orders by order date
       
        if(!order.isEmpty()) //if the HashMap is not empty
        {
            int orderID = myView.GetOrderIDInput();
            boolean orderIDExists = myView.OrderIDExists(order, orderID); //check to see if the order ID exists
        
            if(orderIDExists)
            {
                Orders editOrder = myView.OrderInfo(orderID, order, products, states); //set a new Order object for the user's choices of edits
                
                if(editOrder != null)
                {
                    editOrder = myService.CalculateInputs(editOrder); //calculates the inputs
                    myView.DisplayOrderSummary(editOrder); //shows the order summary to the user
                    boolean doEdit = myView.GetConfirmationInput("edit"); //user confirms to edit
       
                    if(doEdit)
                    {
                        myService.EditOrder(orderID, editOrder, orderDate); //edits the order ID that was given from the user
                        myView.DisplaySuccessfulBanner("Order successfully edited");
                    }
                } else
                {
                    myView.DisplaySuccessfulBanner("There were no edits made");
                }
            } else
            {
                myView.DisplayErrorMessage("Order ID does not exist for that date.");
                EditOrder(); //recursion - go back through the edit choices for the user
            }
        } else
        {
            myView.DisplayErrorMessage("There are no orders to edit for that date.");
        }
    }
    
    /**
     * RemoveOrder - removes an order ID by the order date
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void RemoveOrder() throws FlooringPersistenceException
    {
        myView.DisplayRemoveOrderBanner();
        
        String orderDate = myView.GetOrderDateInput("remove");
        
        //gets the order list of the order date the user chosen
        Map<Integer, Orders> order = myService.GetOrdersByDate(orderDate);
        
        if(!order.isEmpty()) //if the HashMap, order, is not empty
        {
            int orderID = myView.GetOrderIDInput(); //get the user's choice for orderID
            Orders getOrderInfo = myService.OrderInfo(order, orderID);
            myView.DisplayOrderSummary(getOrderInfo);
            boolean orderIDExists = myView.OrderIDExists(order, orderID); //check to see if the order ID exists
            
            if(orderIDExists)
            {
                //myView.DisplayOrderSummary(getOrderInfo);
                boolean doRemove = myView.GetConfirmationInput("remove"); //user confirms if they want to remove the order
        
                if(doRemove)
                {      
                    myService.RemoveOrder(orderID, orderDate); //removes the orderID by the order date
                    myView.DisplayRemoveResult(null);
                }
            } else
            {
                myView.DisplayErrorMessage("Order ID does not exist for that date.");
                RemoveOrder(); //recursion - go back through the choices for the user
            }
        } else
        {
            myView.DisplayErrorMessage("There are no orders to remove for that date.");
        }
    }
    
    /**
     * ExportData - this goes to the view to display the export banner and then
     * it will display a successful export banner. The user cannot see the export
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void ExportData() throws FlooringPersistenceException
    {
        myView.DisplayExportDataBanner();
        myService.ExportAllOrders();
        myView.DisplaySuccessfulBanner("All of the orders were successfully exported");
    }
    
    /**
     * ExitMessage - goes to the view to print an exit message to the user when
     * they choose to exit the program
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    private void ExitMessage() throws FlooringPersistenceException
    {
        myView.DisplayExitMessage();
    }
    
    /**
     * UnknownCommand - goes to the view to print an unknown command
     * -------------------------------------------------------------------------
     * @param menuSelection
     * @throws FlooringPersistenceException 
     */
    private void UnknownCommand(int menuSelection) throws FlooringPersistenceException
    {
        myView.DisplayUnknownCommand(menuSelection);
    }
}