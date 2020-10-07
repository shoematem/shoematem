/**
 * VendingMachineView - this class prints out anything to the user that they
 * need to view or give input for the program
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/24/2020
 */
package VendingMachine.UI;

import VendingMachine.DTO.VendingMachine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineView
{
    private final UserIO myIO;
    
    /**
     * VendingMachineView - a constructor that sets the myIO field
     * -------------------------------------------------------------------------
     * @param myIO - an instance of the UserIO class
     */
    public VendingMachineView(UserIO myIO)
    {
        this.myIO = myIO;
    }
    
    /**
     * PrintMainMenuGetSelection - prints the menu choices to the user and the
     * user selects which choice
     * -------------------------------------------------------------------------
     * @return - the user choice of the menu
     */
    public int PrintMainMenuGetSelection()
    {
        myIO.print("-------------------------------");
        myIO.print("Welcome to the Vending Machine!");
        myIO.print("-------------------------------");
        myIO.print("1. View Vending Machine Inventory");
        myIO.print("2. Input Money.");
        myIO.print("3. Exit");
        
        return myIO.readInt("Please select from the above choices.", 1, 3);
    }
    
    /**
     * PrintDrinkTypeMenuGetSelection - prints the drink type choices to the user
     * and the user selects which type of drink they want
     * -------------------------------------------------------------------------
     * @return - the user choice of the menu
     */
    public int PrintDrinkTypeMenuGetSelection()
    {
        myIO.print("Drink Types");
        myIO.print("1. Alcohol");
        myIO.print("2. Energy/Caffeinated");
        myIO.print("3. Juice");
        myIO.print("4. Soda/Pop");
        myIO.print("5. Sports");
        myIO.print("6. Water");
        myIO.print("7. Other");
        myIO.print("8. Cancel Purchase");
        
        return myIO.readInt("Please select from the above choices.", 1, 8);
    }
    
    /**
     * DisplayVendingInventory - this grabs a list of the drinks inventory
     * and prints it to the user. The user then selects enter to go back to the
     * main menu
     * -------------------------------------------------------------------------
     * @param vmList - a list of the drink inventory
     */
    public void DisplayVendingInventory(List<VendingMachine> vmList)
    {
        String vmInfo;
        
        myIO.print("");
        
        for(VendingMachine currentVM : vmList)
        {
            vmInfo = currentVM.getDrinkName();
            vmInfo += " - " + currentVM.getNumInventory();
            
            myIO.print(vmInfo);
        }
        
        myIO.readString("Please hit enter to continue.");
    }
    
    /**
     * DisplayMoneyInput - this gets the user's money input. The parameter,
     * difference, is strictly to check if the user has already input money, or
     * not. If they have, it will then ask for more money. The parameter, difference,
     * has the difference of user money and the drink price they originally wanted
     * -------------------------------------------------------------------------
     * @param difference - whether or not the user has already been in the program
     * @return 
     */
    public String DisplayMoneyInput(String difference)
    {
        boolean needMoreMoney = true;
        String userMoney = "";
        
        if(difference.equals("0"))
        {
            userMoney = myIO.readString("Please enter an amount you would like to enter into the vending machine.");
        } else
        {
            do
            {
                try
                {
                    userMoney = myIO.readString("Please enter at least $" + difference.replace("-", "") + " more.");
                    
                    if(difference.startsWith("0") && userMoney.startsWith("."))
                    {
                        userMoney = "0" + userMoney;
                    }
                    
                    if(userMoney.length() == 3)
                    {
                        userMoney += "0";
                    }
                    
                    if(userMoney.compareTo(difference) < 0)
                    {
                        DisplayErrorMessage("Money not taken! You did not input enough!");
                    } else
                    {
                        needMoreMoney = false;
                    }
                } catch(NumberFormatException e)
                {
                    DisplayErrorMessage("You did not enter a number, try again.");
                }
            } while(needMoreMoney);
        }
        
        return userMoney;
    }
    
    /**
     * KeepGoing - this method asks the user if they want to keep going in the
     * program. This is only called when the user does not have enough money
     * -------------------------------------------------------------------------
     * @return - true if they want to keep going, false otherwise
     */
    public boolean KeepGoing()
    {
        boolean keepGoing = true;
        boolean goodInput;
        String userChoice;
        
        do
        {
            userChoice = myIO.readString("Would you like to keep going? (y/n)");
            goodInput = true;
            if(!userChoice.equals("y") && !userChoice.equals("n"))
            {
                DisplayErrorMessage("You did not input y or n. Try again.");
            } 
        } while(!goodInput);
        
        if(userChoice.equals("y"))
        {
            return keepGoing;
        } else
        {
            return !keepGoing;
        }
    }
    
    /**
     * DisplayDrinkType - this grabs the HashMap of drinks from the parameter and
     * then iterates the map to put values into multiple arrays for later use in
     * this method to display to the user and to format the new HashMap to include
     * the drink inventory number. The method will then print the drink names and
     * prices to the user and the user chooses which drink they want
     * -------------------------------------------------------------------------
     * @param vmList - HashMap of the list of drinks
     * @return - HashMap with the chosen drink
     */
    public Map<String, String> DisplayDrinkType(Map<String, String> vmList)
    {   
        int drinkChoice;
        int i = 0;
        
        String[] drinkArr;
        String[] drinkInventory = new String[vmList.size()];
        String[] drinkName = new String[vmList.size()];
        String[] drinkPrice = new String[vmList.size()];
        
        Map<String, String> vendedItem = new HashMap<>();
        
        myIO.print("");
        
        if(!vmList.isEmpty())
        {
            for(String vmKey : vmList.keySet())
            {
                drinkName[i] = vmKey;
                
                drinkArr = vmList.get(vmKey).split("/");
                drinkPrice[i] = drinkArr[0];
                drinkInventory[i] = drinkArr[1];

                i++;
                
                myIO.print(i + ". " + vmKey + " - $" + drinkArr[0]);
            }
        }
        
        drinkChoice = myIO.readInt("Please choose from one of the above choices.", 1, i);
        
        if(!drinkInventory[drinkChoice - 1].equals("0") && !drinkInventory[drinkChoice - 1].startsWith("-"))
        {
            vendedItem.put(drinkName[drinkChoice - 1], drinkPrice[drinkChoice - 1]);
        }
        
        return vendedItem;
    }
    
    /**
     * DisplayPrintCashBack - this prints out the money the user is getting back
     * -------------------------------------------------------------------------
     * @param difference - brings in an array of 4 possible values (quarters,
     * dimes, nickels, and pennies)
     */
    public void DisplayPrintCashBack(String difference)
    {
        String[] cashBack;
        
        difference = difference.replace("[","");
        difference = difference.replace("]","");
        cashBack = difference.split(",");
        
        myIO.print("Please wait to receive your change.\n");

        if(!cashBack[0].trim().equals("0"))
        {
            myIO.print(cashBack[0] + " Quarters");
        }
        
        if(!cashBack[1].trim().equals("0"))
        {
            myIO.print(cashBack[1] + " Dimes");
        }
        
        if(!cashBack[2].trim().equals("0"))
        {
            myIO.print(cashBack[2] + " Nickels");
        }
        
        if(!cashBack[3].trim().equals("0"))
        {
            myIO.print(cashBack[3] + " Pennies");
        }
        
        myIO.readString("Please hit enter to take your change.");
    }
    
    /**
     * DisplayViewInventoryBanner - prints the vending machine inventory to the
     * user
     */
    public void DisplayViewInventoryBanner()
    {
        myIO.print("=== Vending Machine Inventory ===");
    }
    
    /**
     * DisplayVendedItem - prints to the user to take their vended drink
     * -------------------------------------------------------------------------
     * @param drinkName - the drink name the user chose
     */
    public void DisplayVendedItem(String drinkName)
    {
        myIO.readString("Please hit enter to take your " + drinkName + ".");
    }
    
    /**
     * DisplayInputMoneyBanner - prints the input money banner to the user
     */
    public void DisplayInputMoneyBanner()
    {
        myIO.print("=== Input Money ===");
    }
    
    /**
     * DisplayExitBanner - prints the unknown command banner to the user
     */
    public void DisplayExitBanner()
    {
        myIO.print("=== Thank you for using the Vending Machine! Good Bye. ===");
    }
    
    /**
     * DisplayUnknownCommandBanner - prints the unknown command banner to the user
     */
    public void DisplayUnknownCommandBanner()
    {
        myIO.print("Unknown Command!");
    }
    
    /**
     * DisplayErrorMessage - prints an error message to the user
     * -------------------------------------------------------------------------
     * @param errorMsg - passes the error message the program needs to relay
     * back to the user
     */
    public void DisplayErrorMessage(String errorMsg)
    {
        myIO.print("=== ERROR ===");
        myIO.print(errorMsg);
    }
}