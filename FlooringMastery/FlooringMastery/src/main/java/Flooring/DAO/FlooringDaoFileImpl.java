/**
 * FlooringDaoFileImpl - loads any files that need to be read and writes to any
 * file that needs to be written to. It reads/writes with the associated order
 * date. It also loads and sets the variables in the DTOs
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.DAO;

import Flooring.DTO.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringDaoFileImpl implements FlooringDao
{
    private String FILE;
    private int highestOrderID = 0;
    private static final String DELIMITER = ",";
    private static int NUMBER_OF_FIELDS = 12;
    
    private static final Map<Integer, Orders> orders = new HashMap<>();
    private static final Map<Integer, Orders> holdOrders = new HashMap<>();
    private static final Map<String, Products> products = new HashMap<>();
    private static final Map<String, StateTaxes> states = new HashMap<>();
    
    /**
     * FlooringDaoFileImpl - default constructor
     */
    public FlooringDaoFileImpl()
    {
        this.FILE = "Orders";
    }
    
    /**
     * FlooringDaoFileImpl - constructor that sets the file name/location
     * -------------------------------------------------------------------------
     * @param orderTextFile - brings in the text file
     */
    public FlooringDaoFileImpl(String orderTextFile)
    {
        this.FILE = orderTextFile;
        
        if(this.FILE.contains("Orders"))
        {
            this.FILE = "files/Orders/" + this.FILE;
        } else if(this.FILE.contains("Products") || this.FILE.contains("Taxes"))
        {
            this.FILE = "files/Data/" + this.FILE;
            NUMBER_OF_FIELDS = 3;
        }
    }
    
    /**
     * WriteOrders - 
     * -------------------------------------------------------------------------
     * @param isCreateOrder
     * @throws FlooringPersistenceException 
     */
    private void WriteOrders(boolean isCreateOrder) throws FlooringPersistenceException
    {
        String ordersAsText;
        List<Orders> ordersList;
        File fileName = new File(FILE);
        PrintWriter out;
        
        if(isCreateOrder)
        {
            ordersList = new ArrayList(holdOrders.values());
        } else
        {
            ordersList = new ArrayList(orders.values());
        }
        
        try
        {
            out = new PrintWriter(new FileWriter(FILE));
        } catch(IOException e)
        {
            throw new FlooringPersistenceException("Could not save order data to the file, " + FILE + ".", e);
        }

        if(fileName.length() == 0)
        {
            if(FILE.contains("Order"))
            {
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            } else
            {
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate");
            }
        }
        
        for(Orders currentOrder : ordersList)
        {
            ordersAsText = MarshallOrders(currentOrder, null, null);
            out.println(ordersAsText);
            out.flush();
        }
        
        out.close();
    }
    
    /**
     * MarshallOrders - 
     * -------------------------------------------------------------------------
     * @param aOrder
     * @param aProduct
     * @param aState
     * @return
     * @throws FlooringPersistenceException 
     */
    private String MarshallOrders(Orders aOrder, Products aProduct, StateTaxes aState) throws FlooringPersistenceException
    {
        String returnToText = "";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate currentDate = LocalDate.parse(LocalDate.now().format(dateFormat), dateFormat);
        
        if(aOrder != null)
        {
            String ordersAsText = aOrder.getOrderID() + DELIMITER;
            ordersAsText += aOrder.getCustomerName() + DELIMITER;
            ordersAsText += aOrder.getState() + DELIMITER;
            ordersAsText += aOrder.getStateTaxRate() + DELIMITER;
            ordersAsText += aOrder.getProductType() + DELIMITER;
            ordersAsText += aOrder.getArea() + DELIMITER;
            ordersAsText += aOrder.getCostPerSqFoot() + DELIMITER;
            ordersAsText += aOrder.getLaborCostPerSqFoot() + DELIMITER;
            ordersAsText += aOrder.getMaterialCost() + DELIMITER;
            ordersAsText += aOrder.getLaborCost() + DELIMITER;
            ordersAsText += aOrder.getCalcTax() + DELIMITER;
            
            if(FILE.contains("Backup"))
            {
                ordersAsText += aOrder.getTotal() + DELIMITER;
                ordersAsText += currentDate;
            } else
            {
                ordersAsText += aOrder.getTotal();
            }
            
            returnToText = ordersAsText;
        } else if(aProduct != null)
        {
            String productsAsText = aProduct.getProductType() + DELIMITER;
            
            returnToText = productsAsText;
        } else if(aState != null)
        {
            String stateTaxesAsText = aState.getStateAbbr() + DELIMITER;
            
            returnToText = stateTaxesAsText;
        }
        
        return returnToText;
    }
    
    /**
     * UnmarshallOrders - 
     * -------------------------------------------------------------------------
     * @param orderAsText
     * @return
     * @throws FlooringPersistenceException 
     */
    private Orders UnmarshallOrders(String orderAsText) throws FlooringPersistenceException
    {
        String[] orderTokens = orderAsText.split(DELIMITER + "(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
        
        if(orderTokens.length == NUMBER_OF_FIELDS && !orderTokens[0].equals("OrderNumber"))
        {            
            for(int i = 0; i < orderTokens.length; i++)
            {
                if(orderTokens[i].contains("\""))
                {
                    orderTokens[i] = orderTokens[i].replaceAll("\"", "");
                }
            }
            
            int orderID = Integer.parseInt(orderTokens[0].trim());
 
            if(highestOrderID < orderID)
            {
                highestOrderID = orderID;
            }
            
            Orders ordersFromFile = new Orders(orderID);
            ordersFromFile.setCustomerName(orderTokens[1].trim());
            ordersFromFile.setState(orderTokens[2].trim());
            ordersFromFile.setStateTaxRate(orderTokens[3].trim());
            ordersFromFile.setProductType(orderTokens[4].trim());
            ordersFromFile.setArea(orderTokens[5].trim());
            ordersFromFile.setCostPerSqFoot(orderTokens[6].trim());
            ordersFromFile.setLaborCostPerSqFoot(orderTokens[7].trim());
            ordersFromFile.setMaterialCost(orderTokens[8].trim());
            ordersFromFile.setLaborCost(orderTokens[9].trim());
            ordersFromFile.setCalcTax(orderTokens[10].trim());
            ordersFromFile.setTotal(orderTokens[11].trim());

            return ordersFromFile;
        } else
        {
            return null;
        }
    }
    
    /**
     * UnmarshallProducts - 
     * -------------------------------------------------------------------------
     * @param productAsText
     * @return
     * @throws FlooringPersistenceException 
     */
    private Products UnmarshallProducts(String productAsText) throws FlooringPersistenceException
    {
        String[] productTokens = productAsText.split(DELIMITER);

        if(productTokens.length ==  NUMBER_OF_FIELDS && !productTokens[0].equals("ProductType"))
        {
            String productName = productTokens[0].trim();
            
            Products productsFromFile = new Products(productName);
            productsFromFile.setCostPerSqFoot(productTokens[1].trim());
            productsFromFile.setLaborCostPerSqFoot(productTokens[2].trim());
            
            return productsFromFile;
        } else
        {
            return null;
        }
    }
    
    /**
     * StateTaxes - 
     * -------------------------------------------------------------------------
     * @param stateAsText
     * @return
     * @throws FlooringPersistenceException 
     */
    private StateTaxes UnmarshallStateTaxes(String stateAsText) throws FlooringPersistenceException
    {
        String[] stateTokens = stateAsText.split(DELIMITER);
        
        if(stateTokens.length == NUMBER_OF_FIELDS && !stateTokens.equals("State"))
        {
            String stateAbbr = stateTokens[0].trim();
            
            StateTaxes statesFromFile = new StateTaxes(stateAbbr);
            statesFromFile.setStateName(stateTokens[1].trim());
            statesFromFile.setTaxRate(stateTokens[2].trim());
            
            return statesFromFile;
        } else
        {
            return null;
        }
    }
    
    /**
     * LoadFiles - 
     * -------------------------------------------------------------------------
     * @param orderDate
     * @param sameFile
     * @throws FlooringPersistenceException 
     */
    private void LoadFiles(String orderDate, boolean sameFile) throws FlooringPersistenceException
    {
        String currentLine;
        Scanner scanner;
        
        //DTOs
        Orders currentOrder = null;
        Products currentProduct = null;
        StateTaxes currentState = null;
        
        if(!orderDate.isEmpty())
        {
            this.FILE = "files/Orders/Orders_" + orderDate + ".txt";
        }
        
        File fileName = new File(FILE);

        if(fileName.exists() && fileName.isFile())
        {
            try
            {
                scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            } catch(FileNotFoundException e)
            {
                throw new FlooringPersistenceException("Could not load the file " + fileName + " into memory.", e);
            }

            while(scanner.hasNextLine())
            {
                currentLine = scanner.nextLine();

                if(FILE.contains("Orders"))
                {
                    currentOrder = UnmarshallOrders(currentLine);
                } else if(FILE.contains("Products"))
                {
                    currentProduct = UnmarshallProducts(currentLine);
                } else if(FILE.contains("Taxes")) //not an else just in case we want to add another file name
                {
                    currentState = UnmarshallStateTaxes(currentLine);
                }

                if(currentOrder != null)
                {
                    if(sameFile)
                    {
                        holdOrders.put(currentOrder.getOrderID(), currentOrder);
                    }
                    
                    orders.put(currentOrder.getOrderID(), currentOrder);
                }

                if(currentProduct != null)
                {
                    products.put(currentProduct.getProductType(), currentProduct);
                }

                if(currentState != null)
                {
                    states.put(currentState.getStateAbbr(), currentState);
                }
            }

            scanner.close();
        }
    }
    
    /**
     * CreateOrder - 
     * -------------------------------------------------------------------------
     * @param order
     * @param orderDate
     * @throws FlooringPersistenceException 
     */
    @Override
    public void CreateOrder(Orders order, String orderDate) throws FlooringPersistenceException
    {
        holdOrders.clear();
        NUMBER_OF_FIELDS = 12;
        this.FILE = "files/Orders/Orders_" + orderDate + ".txt";
        ExportAllOrders(false);
        this.FILE = "files/Orders/Orders_" + orderDate + ".txt";

        highestOrderID++;
        order.setOrderID(highestOrderID);
        holdOrders.put(highestOrderID, order);

        WriteOrders(true);
    }

    /**
     * RemoveOrder - 
     * -------------------------------------------------------------------------
     * @param orderID
     * @param orderDate
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Orders RemoveOrder(int orderID, String orderDate) throws FlooringPersistenceException
    {
        orders.clear();
        LoadFiles(orderDate, true);
        Orders removedOrder = orders.remove(orderID);
        WriteOrders(false);
        
        return removedOrder;
    }

    /**
     * EditOrder - 
     * -------------------------------------------------------------------------
     * @param orderID
     * @param order
     * @param orderDate
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Orders EditOrder(int orderID, Orders order, String orderDate) throws FlooringPersistenceException
    {
        orders.clear();
        NUMBER_OF_FIELDS = 12;
        LoadFiles(orderDate, true);
        Orders editedOrder = orders.put(orderID, order);
        WriteOrders(false);
        
        return editedOrder;
    }

    /**
     * ExportAllOrders - exports all orders to a backup file
     * -------------------------------------------------------------------------
     * @param isExport - check to see if the program is coming into this method
     * from the menu choice of Export Orders
     * @throws FlooringPersistenceException 
     */
    @Override
    public void ExportAllOrders(boolean isExport) throws FlooringPersistenceException
    {   
        orders.clear();
        boolean sameFile = false;
        File holdFile = new File(this.FILE);
        File folder = new File("files/Orders");
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles)
        {
            if(file.isFile())
            {          
                if(holdFile.equals(file))
                {
                    sameFile = true;
                }
                
                this.FILE = file.toString();
                LoadFiles("", sameFile);
                sameFile = false;
            }
        }
        
        if(isExport)
        {
            this.FILE = "files/Backup/DataExport.txt";
            WriteOrders(false);
        }
    }

    /**
     * GetOrdersByDate - 
     * -------------------------------------------------------------------------
     * @param orderDate
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<Integer, Orders> GetOrdersByDate(String orderDate) throws FlooringPersistenceException
    {
        orders.clear();
        NUMBER_OF_FIELDS = 12;
        LoadFiles(orderDate, false);
        
        return orders;
    }
    
    /**
     * GetProducts - 
     * -------------------------------------------------------------------------
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<String, Products> GetProducts() throws FlooringPersistenceException
    {
        LoadFiles("", false);
        
        return products;
    }
    
    /**
     * GetStateTaxes - 
     * -------------------------------------------------------------------------
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<String, StateTaxes> GetStateTaxes() throws FlooringPersistenceException
    {
        LoadFiles("", false);
        
        return states;
    }
}