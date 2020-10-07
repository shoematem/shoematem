/**
 * VendingMachineController - this is the controller of the program. It is the
 * overall backbone of the program. It has loops inside for user input and
 * directs the program on where to go for each function/method.
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */

package VendingMachine.Controller;

import VendingMachine.DAO.VendingMachinePersistenceException;
import VendingMachine.DTO.VendingChange;
import VendingMachine.DTO.VendingMachine;
import VendingMachine.Service.VendingMachineServiceLayer;
import VendingMachine.UI.VendingMachineView;
import java.util.List;
import java.util.Map;

public class VendingMachineController
{
    private VendingMachineView myView;
    private VendingMachineServiceLayer myService;
    private VendingChange myChange = new VendingChange();
    
    /**
     * VendingMachineController - constructor for the class. It sets the fields
     * myView and myService
     * -------------------------------------------------------------------------
     * @param myView - sets myView with this.myView
     * @param myService - sets myService with this.myService
     */
    public VendingMachineController(VendingMachineView myView, VendingMachineServiceLayer myService)
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
        int menuSelection = 0;

        try
        {
            do
            {
                menuSelection = GetMainMenuSelection(); //get the user's menu selection
                
                switch(menuSelection)
                {
                    case 1:
                        ListVendingInventory(); //lists the total inventory of each drink
                        break;
                    case 2:
                        GetUserMoney("0"); //get the user's money to start the transaction
                        
                        //show the drink type and then the drinks under that type - gets the user's
                        //choice for a drink and gives money back
                        RunDrinkType();
                        myView.DisplayExitBanner();
                        
                        keepGoing = false;
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        UnknownCommand();
                }
            }
            while(keepGoing);
        } catch(VendingMachinePersistenceException e)
        {
            myView.DisplayErrorMessage(e.getMessage());
        }
        
        //this is strictly to keep in the vending machine until the user explicitly selects exit
        if(menuSelection == 3)
        {
            ExitMessage();
        } else
        {
            RunApp();
        }
    }
    
    /**
     * RunDrinkType - it loops through the drink type's menu and gets the user's
     * selection for drink type
     */
    private void RunDrinkType()
    {
        boolean keepGoing = true;
        int menuSelection;
        
        try
        {
            do
            {
                menuSelection = GetDrinkTypeMenuSelection();
                
                switch(menuSelection)
                {
                    case 1: //Alcohol
                        DisplayDrinksFromType("Alcohol");
                        break;
                    case 2: //Energy/Caffeinated
                        DisplayDrinksFromType("EnergyCaffeinated");
                        break;
                    case 3: //Juice
                        DisplayDrinksFromType("Juice");
                        break;
                    case 4: //Soda/Pop
                        DisplayDrinksFromType("SodaPop");
                        break;
                    case 5: //Sports
                        DisplayDrinksFromType("Sports");
                        break;
                    case 6: //Water
                        DisplayDrinksFromType("Water");
                        break;
                    case 7: //Other
                        DisplayDrinksFromType("Other");
                        break;
                    case 8: //Cancel
                        keepGoing = false;
                        break;
                    default:
                        UnknownCommand();
                }
                
                if(menuSelection >= 1 && menuSelection <= 7) //menu selection is between 1 - 7
                {
                    keepGoing = false;
                } 
            } while(keepGoing);
        } catch(VendingMachinePersistenceException e)
        {
            myView.DisplayErrorMessage(e.getMessage());
        }
    }
    
    /**
     * GetUserMoney - asks and receives the user's money. The user inputs what
     * amount they want to do put in my externally going to myView.DisplayMoneyInput.
     * The parameter, difference, is here for the 2nd+ time to check if this
     * method has been ran before
     * -------------------------------------------------------------------------
     * @param difference - grabs the difference of the user money and vended item price
     * @throws VendingMachinePersistenceException 
     */
    private void GetUserMoney(String difference) throws VendingMachinePersistenceException
    {
        String userMoney;
        
        myView.DisplayInputMoneyBanner();
      
        if(!difference.equals("0"))
        {
            userMoney = myView.DisplayMoneyInput(difference);
            myChange.CalcUserMoney(myChange.getUserMoney(), userMoney);
        } else
        {
            userMoney = myView.DisplayMoneyInput("0");
            myChange.setUserMoney(userMoney);
        }
    }
    
    /**
     * ListVendingInventory - this method is the start to getting the list of drinks
     * and how much is in its inventory. It goes externally to myService to grab
     * the list. It will then go to myView to display the inventory
     * -------------------------------------------------------------------------
     * @throws VendingMachinePersistenceException 
     */
    private void ListVendingInventory() throws VendingMachinePersistenceException
    {
        myView.DisplayViewInventoryBanner();
        List<VendingMachine> vmList = myService.ListVendingInventory();
        myView.DisplayVendingInventory(vmList);
    }
    
    /**
     * DisplayDrinksFromType - this method displays the drinks that were chosen
     * from the user for drink type (Alcohol, Soda/Pop, etc.). It goes externally
     * to grab the drink type, calculate the change back to the user, and removes
     * the item.
     * -------------------------------------------------------------------------
     * @param drinkType
     * @throws VendingMachinePersistenceException 
     */
    private void DisplayDrinksFromType(String drinkType) throws VendingMachinePersistenceException
    {   
        String difference = "", drinkPrice = "", drinkName = "";
        //declare vmList and assign it the value of the list of drinks by type
        Map<String, String> vmList = myService.DisplayDrinksFromType(drinkType);
        vmList = myView.DisplayDrinkType(vmList);
        
        //if vmList is empty, then the user chose a drink with no inventory
        if(!vmList.isEmpty())
        {
            //iterate for each key set
            for(String vmKey : vmList.keySet())
            {
                drinkName = vmKey; //= vmKey -> the key of the HashMap
                drinkPrice = vmList.get(vmKey); //assign the value of the HashMap
            }

            //calculate the difference between the price of the drink and user's money
            difference = myService.GetUserMoney(drinkPrice, myChange.getUserMoney());

            if(!difference.startsWith("-")) //if the difference is not negative
            {
                myService.RemoveVendedItem(drinkName); //remove (-1) the drink in the inventory

                //if it contains a "," that means that there is money given back, it was assigned
                //to a String array in myService.GetUserMoney
                if(difference.contains(","))
                {
                    myView.DisplayPrintCashBack(difference); //display the cash back to the user
                }

                myView.DisplayVendedItem(drinkName);
            } else
            {
                 //replace the - amount with positive; this to show what the user now needs to put into the vending machine
                difference = difference.replace("-", "");

                myView.DisplayErrorMessage("You did not input enough money! You entered $" + myChange.getUserMoney()
                    + ". You needed $" + drinkPrice + ". You need $" + difference + " more.");
                
                KeepGoing(false, difference); //false is if there is an inventory, pass the difference
            }
        } else
        {
            myView.DisplayErrorMessage("\nYou chose a drink with no inventory.");
            
            KeepGoing(true, difference); //true if there is no inventory, pass the difference which is blank
        }
    }
    
    /**
     * KeepGoing - checks to see if the user wants to keep going with the vending
     * machine. If they want to keep going, it prompts for more money and will
     * loop back through the drink types. If they want to exit, it will give the
     * user their money back
     * -------------------------------------------------------------------------
     * @param noInventory - true if no inventory, false if there is inventory
     * @param difference - value of the difference, will be blank if there is no inventory
     * @throws VendingMachinePersistenceException 
     */
    private void KeepGoing(boolean noInventory, String difference) throws VendingMachinePersistenceException
    {
        boolean keepGoing = myView.KeepGoing();
        
        if(keepGoing)
        {
            if(!noInventory)
            {
                GetUserMoney(difference); //get more money from the user
            }
            
            RunDrinkType(); //go back to the drink type menu
        } else
        {
            //give the money back to the user
            difference = myService.GetUserMoney("0", myChange.getUserMoney());
            myView.DisplayPrintCashBack(difference);
        }
    }
    
    /**
     * GetDrinkTypeMenuSelection - returns the drink type the user inputted
     * -------------------------------------------------------------------------
     * @return - drink type the user inputted
     */
    private int GetDrinkTypeMenuSelection()
    {
        return myView.PrintDrinkTypeMenuGetSelection();
    }
    
    /**
     * GetMainMenuSelection - returns the main menu selection the user inputted
     * -------------------------------------------------------------------------
     * @return - main menu selection
     */
    private int GetMainMenuSelection()
    {
        return myView.PrintMainMenuGetSelection();
    }
    
    /**
     * UnknownCommand - goes externally to print the unknown command
     */
    private void UnknownCommand()
    {
        myView.DisplayUnknownCommandBanner();
    }
    
    /**
     * ExitMessage - goes externally to print the exit message
     */
    private void ExitMessage()
    {
        myView.DisplayExitBanner();
    }
}