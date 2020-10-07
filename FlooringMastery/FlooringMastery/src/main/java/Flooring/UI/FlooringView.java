/**
 * FlooringView - this class prints out anything to the user that they
 * need to view or give input for the program
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.UI;

import Flooring.DAO.FlooringPersistenceException;
import Flooring.DTO.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Map;

public class FlooringView
{
    private final UserIO myIO;
    
    /**
     * FlooringView - constructor to set the UserIO class
     * -------------------------------------------------------------------------
     * @param myIO 
     */
    public FlooringView(UserIO myIO)
    {
        this.myIO = myIO;
    }
    
    /**
     * PrintMenuGetSelection - prints the main menu to the user and they select
     * the choice they want
     * -------------------------------------------------------------------------
     * @return - the user's choice
     */
    public int PrintMenuGetSelection()
    {
        myIO.print("--------------------------------");
        myIO.print("Welcome to the Flooring Program.");
        myIO.print("--------------------------------");
        myIO.print("1. Display Orders");
        myIO.print("2. Add an Order");
        myIO.print("3. Edit an Order");
        myIO.print("4. Remove an Order");
        myIO.print("5. Export All Data");
        myIO.print("6. Quit Program");
        myIO.print("--------------------------------");
        
        return myIO.readInt("Please choose from one of the above choices.", 1, 6);
    }
    
    /**
     * GetOrderIDInput - gets the user's choice for the order ID
     * -------------------------------------------------------------------------
     * @return - the user's input for order ID
     */
    public int GetOrderIDInput()
    {
        return myIO.readInt("Please enter an Order ID.");
    }
    
    /**
     * GetOrderDateInput - gets the user's choice for the order date and then
     * formats it into a readable format for the program -> MMddyyyy
     * -------------------------------------------------------------------------
     * @param menuType
     * @return - order date input from the user (formatted to MMddyyyy)
     */
    public String GetOrderDateInput(String menuType)
    {
        boolean goodInput = false;
        String orderDate = "";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy"); //format for LocalDate MM/dd/yyyy (ex: 01/01/2000)
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("MMddyyyy"); //format for LocalDate MMddyyyy (ex: 01012000)
        LocalDate currentDate = LocalDate.parse(LocalDate.now().format(dateFormat), dateFormat); //parse today's date and format it

        do
        {
            orderDate = myIO.readString("Please enter an Order Date in the format of mm/dd/yyyy.");
        
            try
            {
                LocalDate inputDate = LocalDate.parse(orderDate, dateFormat); //inputDate -> the date from the user
                orderDate = inputDate.format(fileFormat); //order date is formatted into a string

                //if the input date is today or before today - if it is, loop back through
                if((inputDate.compareTo(currentDate) <= 0) && menuType.equals("create"))
                {
                    myIO.print("The date must be in the future!");
                    continue;
                }
                
                goodInput = true;
            } catch(DateTimeParseException e)
            {
                myIO.readString(orderDate + " is not in proper format (mm/dd/yyyy). Please hit enter to continue.");
            }
        } while(!goodInput);
        
        return orderDate;
    }
    
    /**
     * DisplayOrderList - displays the order list by order date to the user
     * -------------------------------------------------------------------------
     * @param order 
     */
    public void DisplayOrderList(Map<Integer, Orders> order)
    {
        String formatString = "";
        String[] orderArr;
        
        if(!order.isEmpty()) //if the map is not empty
        {
            for(int orderKey : order.keySet()) //loop through the order HashMap
            {
                //convert the order values to a string and split it
                orderArr = order.get(orderKey).toString().split("/");
                formatString += "\nOrder - " + orderArr[0];
                
                for(int i = 1; i < orderArr.length; i++) //loop through the orderArr length to format the string
                {
                    formatString += " " + orderArr[i];
                }
            }
        }
        
        myIO.print(formatString);
    }
    
    /**
     * OrderInfo - this is for either a new or edit order. It grabs all the user's
     * input (customer name, state, product type, and area) and formats it for the
     * service layer's calculation method.
     * -------------------------------------------------------------------------
     * @param orderID
     * @param newOrder
     * @param products
     * @param states
     * @return 
     */
    public Orders OrderInfo(int orderID, Map<Integer, Orders> newOrder, Map<String, String> products, Map<String, String> states)
    {
        boolean goodInput = false;
        
        String area = "", customerName = "", productType = "", state = "";
        String holdArea = "", holdCalcTax = "", holdCostPerSqFoot = "", holdCustomerName = "", holdLaborCost = "",
                holdLaborCostPerSqFoot = "", holdMaterialCost = "", holdProductType = "", holdState = "",
                holdStateTaxRate = "", holdTotal = "";
        String[] newOrderArr;
        Orders order;
        
        //if the orderID is 0 then it is a create order, if not then it is an edit order
        if(orderID == 0)
        {
            order = new Orders(orderID);
        } else
        {
            order = new Orders(orderID);
        }
        
        //if the newOrder is not empty then it is an edit order
        if(!newOrder.isEmpty())
        {
            for(int orderKey : newOrder.keySet()) //loop through the new order HashMap
            {
                if(orderKey == orderID) //if the order ID exists
                {
                    //for the below, have the holdCustomerName equal to the current order
                    newOrderArr = newOrder.get(orderKey).toString().split("/");
                    
                    holdCustomerName = newOrderArr[1];
                    holdProductType = newOrderArr[2];
                    holdState = newOrderArr[3];
                    holdArea = newOrderArr[4];
                    holdCostPerSqFoot = newOrderArr[5];
                    holdLaborCost = newOrderArr[6];
                    holdLaborCostPerSqFoot = newOrderArr[7];
                    holdMaterialCost = newOrderArr[8];
                    holdStateTaxRate = newOrderArr[9];
                    holdCalcTax = newOrderArr[10];
                    holdTotal = newOrderArr[11];
                    
                    customerName = myIO.readString("The current name on this order is " + holdCustomerName
                    + ". Enter a new name or press enter to continue.");
                    state = DisplayStateTaxesOutput(states, orderID, holdState);
                    productType = DisplayProductOutput(products, orderID, holdProductType);
                    
                    if(customerName.equals(""))
                    {
                        customerName = holdCustomerName;
                    }
                }
            }
        } else
        {
            customerName = myIO.readString("Please enter a customer name.");
            state = DisplayStateTaxesOutput(states, orderID, "");
            productType = DisplayProductOutput(products, orderID, "");
        }
        
        if(customerName.contains(","))
        {
            customerName = "\"" + customerName + "\"";
        }
        
        //loop through to get the correct input for area, we use a do-while to go
        //into the loop at least once to grab the area. If the area is a positive
        //integer greater than 100, then it is a good input
        do
        {
            try
            {
                if(holdArea.equals("")) //this is true if it is a new order
                {
                    area = myIO.readString("Please enter an area.");
                } else
                {
                    area = myIO.readString("The current area on the order is " + holdArea
                    + ". Enter a new area or press enter to continue.");
                }
                
                if(area.startsWith("-")) //negative integer
                {
                    myIO.readString("Area must be positive. Press enter to continue.");
                } else if(area.equals("") && orderID != 0)
                {
                    area = holdArea;
                    goodInput = true;
                } else if(Double.parseDouble(area) <= 100) //area is less than or equal to 100
                {
                    myIO.readString("Area must have a minimum of 100. Press enter to continue.");
                } else
                {
                    goodInput = true;
                }
            } catch(NumberFormatException e)
            {
                myIO.readString(area + " is not a number. Press enter to try again.");
            }
        } while(!goodInput);
        
        //we do this to check if each is equal to each other, if it does, then the user pressed
        //enter for every single input option, so we set order to null
        if(customerName.equals(holdCustomerName) && state.equals(holdState) && productType.equals(holdProductType) && area.equals(holdArea))
        {
            order = null;
        } else
        {
            order.setCustomerName(customerName);
            order.setState(state);
            order.setProductType(productType);
            order.setArea(area);
            order.setCostPerSqFoot(holdCostPerSqFoot);
            order.setStateTaxRate(holdStateTaxRate);
            order.setLaborCost(holdLaborCost);
            order.setLaborCostPerSqFoot(holdLaborCostPerSqFoot);
            order.setMaterialCost(holdMaterialCost);
            order.setCalcTax(holdCalcTax);
            order.setTotal(holdTotal);
        }
        
        return order;
    }
    
     /**
     * DisplayStateTaxesOutput - this grabs the state the user entered and checks
     * whether or not the state exists in the StateTaxes file or if it is a valid
     * US state
     * -------------------------------------------------------------------------
     * @param states
     * @param orderID
     * @param currentState - this is not blank if it is an edit order
     * @return 
     */
    private String DisplayStateTaxesOutput(Map<String, String> states, int orderID, String holdState)
    {
        boolean goodInput = false;
        String getState = "";
        String[] stateArr = null;
        StateTaxes state = new StateTaxes();
        
        do
        {
            //getState is set to not blank once it goes through the do-while at least once
            if(!getState.equals(""))
            {
                myIO.readString(stateArr[1] + " is not a state we sell in. Please hit enter to continue.");
            }
            
            if(holdState.equals(""))
            {
                getState = myIO.readString("Please enter a state.").trim();
            } else
            {
                getState = myIO.readString("The current state on this order is " + holdState
                        + ". Enter a new state or press enter to continue.");
            }
            
            //if state entered is 1 character and it is not blank
            if(getState.length() < 2 && !getState.equals(""))
            {
                myIO.readString("Please enter a proper abbreviation or state name. Please hit enter to continue.");
                getState = "";
                continue;
            } else if(getState.equals("") && orderID != 0) //if state equals blank and the order ID is not blank (order is being edited)
            {
                getState = "";
                break;
            }
            
            getState = state.CheckStateExists(getState); //check if state exists in the StateTaxes DTO file
            stateArr = getState.split(","); //split the getState variable (ex: WA, Washington)
        
            for(String stateKey : states.keySet()) //loop through the states HashMap
            {
                if(stateKey.contains(stateArr[0]))
                {
                    getState = stateKey + "/" + states.get(stateKey); //format the string to be used later
                    goodInput = true;
                }
            }
        } while(!goodInput);
        
        if(getState.equals(""))
        {
            getState = holdState;
        }
        
        return getState;
    }
    
    /**
     * DisplayProductOutput - this prints out the available product types to the
     * user and the user chooses their product choice. It will then return the
     * product's information from the Product file
     * @param products
     * @param orderID
     * @param holdProductType - this is not blank if it is not an edit order
     * @return 
     */
    private String DisplayProductOutput(Map<String, String> products, int orderID, String holdProductType)
    {
        int numProducts = 0, userChoice = 0;
        String productType = "";
        String[] productArr;
        String[] productName = new String[products.size()];
        String[] costPerSqFoot = new String[products.size()];
        String[] laborCostPerSqFoot = new String[products.size()];
        
        myIO.print("-------------------------------------------");
        myIO.print("Please choose from the following products\n");
        
        for(String productKey : products.keySet()) //loop through the products HashMap
        {
            productName[numProducts] = productKey;
            productArr = products.get(productKey).split("/"); //split the string from the formatted output
            costPerSqFoot[numProducts] = productArr[0];
            laborCostPerSqFoot[numProducts] = productArr[1];
            
            numProducts++;
            myIO.print(numProducts + ". " + productKey); //print the menu choices to the user
        }
        
        myIO.print("\n-------------------------------------------");
        
        if(holdProductType.equals(""))
        {
            userChoice = myIO.readInt("Please choose from the above choices (1 - " + numProducts + ").", 1, numProducts);
        } else
        {
            userChoice = myIO.readInt("The current product on this order is " + holdProductType
                    + ". Press enter to continue or choose from the above choices (1 - "
                    + numProducts + ").", 1, numProducts);
        }
        
        if(userChoice != -1) //if it is -1 -> the user simply pressed "enter" to not make a choice
        {
            productType = productName[userChoice - 1] + "/"
                + costPerSqFoot[userChoice - 1] + "/"
                + laborCostPerSqFoot[userChoice - 1];
        } else if(orderID == 0) //if it is -1 -> this is not valid if the orderID is 0 (creating new order)
        {
            DisplayProductOutput(products, orderID, holdProductType);
        } else
        {
            productType = holdProductType;
        }
        
        return productType;
    }
    
    /**
     * OrderIDExists - checks to see if the order ID given from the user exists
     * -------------------------------------------------------------------------
     * @param order
     * @param orderID
     * @return true if the order ID exists; false if the order ID does not exist
     */
    public boolean OrderIDExists(Map<Integer, Orders> order, int orderID)
    {
        boolean exists = false;
        
        for(int orderKey : order.keySet()) //loop through the order HashMap
        {
            if(orderKey == orderID) //found the order ID
            {
                exists = true;
                break;
            }
        }
        
        if(!exists) //if the order ID does not exist
        {
            myIO.readString("Order " + orderID + " does not exist in the date entered. Please press enter to continue.");
        }
        
        return exists;
    }
    
    /**
     * GetConfirmationInput - asks the user for each of the menu types (parameter)
     * and will return true (if they confirm - yes) or false (if they do not
     * confirm - no)
     * -------------------------------------------------------------------------
     * @param menuType
     * @return true or false for a confirmation
     * @throws FlooringPersistenceException 
     */
    public boolean GetConfirmationInput(String menuType) throws FlooringPersistenceException
    {
        boolean isConfirm = false, goodInput = false;
        String userChoice = "";
        
        do
        {
            switch (menuType)
            {
                case "create":
                    userChoice = myIO.readString("Are you sure you want to create this order? (y/n)");
                    break;
                case "edit":
                    userChoice = myIO.readString("Are you sure you want to edit this order? (y/n)");
                    break;
                case "remove":
                    userChoice = myIO.readString("Are you sure you want to remove this order? (y/n)");
                    break;
                default:
                    //just in case, for whatever reason, the program comes into here without any of the above menu types
                    throw new FlooringPersistenceException("Menu type does not equal create, edit, or remove.");
            }
            
            if(userChoice.equals("y") || userChoice.equals("n"))
            {
                goodInput = true;
                
                if(userChoice.equals("y"))
                {
                    isConfirm = true; //user confirmed yes "y"
                }
            } else
            {
                DisplayErrorMessage("You did not enter 'y' or 'n' - please try again.");
            }
        } while(!goodInput);
        
        return isConfirm;
    }
    
    /**
     * DisplayOrderSummary - displays the summary of an order whether it is a new
     * order or it is an order that is currently being edited
     * -------------------------------------------------------------------------
     * @param order 
     */
    public void DisplayOrderSummary(Orders order)
    {
        String formatString = "=== Order Summary ===";
        formatString += "\nCustomer name: " + order.getCustomerName();
        formatString += "\nState: " + order.getState();
        formatString += "\nState tax rate: " + order.getStateTaxRate();
        formatString += "\nProduct Type: " + order.getProductType();
        formatString += "\nArea: " + order.getArea();
        formatString += "\nMaterial cost: $" + order.getMaterialCost();
        formatString += "\nLabor cost: $" + order.getLaborCost();
        formatString += "\nLabor cost per square foot: $" + order.getLaborCostPerSqFoot();
        formatString += "\nCost per square foot: $" + order.getCostPerSqFoot();
        formatString += "\nTax: $" + order.getCalcTax();
        formatString += "\nTotal: $" + order.getTotal();
        
        myIO.print(formatString);
        myIO.readString("\nPress enter when you are done reviewing the summary.");
    }
    
    /**
     * DisplayRemoveResult - this prints out if the order ID was successfully
     * removed
     * -------------------------------------------------------------------------
     * @param orderID 
     */
    public void DisplayRemoveResult(Orders orderID)
    {
        if(orderID != null)
        {
            myIO.print("Order " + orderID + " was successfully removed.");
        }
        
        myIO.readString("Please hit enter to continue.");
    }
    
    public void DisplayAddOrderBanner()
    {
        myIO.print("=== Add Order ===");
    }
    
    public void DisplayGetOrderBanner()
    {
        myIO.print("=== Get Order ===");
    }
    
    public void DisplayEditOrderBanner()
    {
        myIO.print("=== Edit Order ===");
    }
    
    public void DisplayRemoveOrderBanner()
    {
        myIO.print("=== Remove Order ===");
    }
    
    public void DisplayExportDataBanner()
    {
        myIO.print("=== Export Data ===");
    }
    
    public void DisplayExitMessage()
    {
        myIO.print("\nThank you for using the Flooring Mastery Program. Good bye.");
    }
    
    public void DisplayErrorMessage(String errorMsg)
    {
        myIO.print("=== ERROR ===");
        myIO.print(errorMsg);
    }
    
    public void DisplayUnknownCommand(int menuSelection)
    {
        myIO.print("\n" + menuSelection + " is not a command!");
    }
    
    /**
     * DisplaySuccessfulBanner - displays any successful message from any of the
     * menu types
     * -------------------------------------------------------------------------
     * @param menuType 
     */
    public void DisplaySuccessfulBanner(String menuType)
    {
        if(!menuType.equals(""))
        {
            myIO.readString(menuType + ". Please hit enter to continue.");
        } else
        {
            myIO.readString("Please hit enter to continue.");
        }
    }
}