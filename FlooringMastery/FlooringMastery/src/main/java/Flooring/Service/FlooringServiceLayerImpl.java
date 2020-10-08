/**
 * FlooringServiceLayerImpl - subclass of the base class FlooringServiceLayer.
 * It overrides each method from FlooringServiceLayer
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.Service;

import Flooring.DAO.*;
import Flooring.DTO.*;
import java.util.HashMap;
import java.util.Map;

public class FlooringServiceLayerImpl implements FlooringServiceLayer
{
    private FlooringDao myDao;
    
    /**
     * FlooringServiceLayerImpl - constructor that sets FlooringDao
     * -------------------------------------------------------------------------
     * @param myDao 
     */
    public FlooringServiceLayerImpl(FlooringDao myDao)
    {
        this.myDao = myDao;
    }
    
    /**
     * CalculateInputs - this method calculates the material cost, labor cost,
     * tax, and total to be shown to the user
     * -------------------------------------------------------------------------
     * @param order
     * @return - a new order object
     */
    @Override
    public Orders CalculateInputs(Orders order)
    {
        String costPerSqFoot, laborCostPerSqFoot, taxRate;
        String area = order.getArea(); //grab the area associated with the order
        String product = order.getProductType(); //grab the product type associated with the order
        String state = order.getState(); //grab the state associated with the order
        String[] productArr, stateArr;
        Orders newOrder = new Orders(order.getOrderID()); //create a new instance of the Orders class
        FlooringCalculations calculate = new FlooringCalculations(); //create a new instance of the FlooringCalculations class
        
        if(product.contains("/"))
        {
            productArr = product.split("/");
            product = productArr[0];
            costPerSqFoot = productArr[1];
            laborCostPerSqFoot = productArr[2];
        } else
        {
            costPerSqFoot = order.getCostPerSqFoot();
            laborCostPerSqFoot = order.getLaborCostPerSqFoot();
        }
        
        if(state.contains("/"))
        {
            stateArr = state.split("/");
            state = stateArr[0];
            taxRate = stateArr[2];
        } else
        {
            taxRate = order.getStateTaxRate();
        }
        
        //calculate the material cost to be set for the order
        String materialCost = calculate.CalcMaterialCost(area, costPerSqFoot);
        //calculate the labor cost to be set for the order
        String laborCost = calculate.CalcLaborCost(area, laborCostPerSqFoot);
        //calculate the tax to be set for the order
        String tax = calculate.CalcTax(taxRate);
        //calculate the total to be set for the order
        String total = calculate.CalcTotal();
        
        //the below sets the newOrder instance to be written
        newOrder.setCustomerName(order.getCustomerName());
        newOrder.setArea(area);
        newOrder.setCostPerSqFoot(costPerSqFoot);
        newOrder.setLaborCost(laborCost);
        newOrder.setLaborCostPerSqFoot(laborCostPerSqFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setProductType(product);
        newOrder.setState(state);
        newOrder.setStateTaxRate(taxRate);
        newOrder.setCalcTax(tax);
        newOrder.setTotal(total);
        
        return newOrder;
    }
    
    /**
     * CreateOrder - gets the order instance of the Order class that the user input
     * to create the order
     * @param order
     * @param orderDate
     * @throws FlooringPersistenceException 
     */
    @Override
    public void CreateOrder(Orders order, String orderDate) throws FlooringPersistenceException
    {
        myDao.CreateOrder(order, orderDate);
    }

    /**
     * RemoveOrder - removes the order from a given order ID
     * @param orderID
     * @param orderDate
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Orders RemoveOrder(int orderID, String orderDate) throws FlooringPersistenceException
    {
        Orders removeOrder = myDao.RemoveOrder(orderID, orderDate);
        
        return removeOrder;
    }

    /**
     * EditOrder - gets the order instance of the Order class that the user input
     * to edit the order of the given order ID
     * -------------------------------------------------------------------------
     * @param orderID
     * @param order
     * @param orderDate
     * @throws FlooringPersistenceException 
     */
    @Override
    public void EditOrder(int orderID, Orders order, String orderDate) throws FlooringPersistenceException
    {   
        Orders newOrder = myDao.EditOrder(orderID, order, orderDate);
    }

    /**
     * Calls the FlooringDao ExportAllOrders method to export every single order
     * in the Order directory
     * -------------------------------------------------------------------------
     * @throws FlooringPersistenceException 
     */
    @Override
    public void ExportAllOrders() throws FlooringPersistenceException
    {
        myDao.ExportAllOrders(true);
    }

    /**
     * GetOrdersByDate - gets the orders by date that the user wants.
     * -------------------------------------------------------------------------
     * @param orderDate
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<Integer, Orders> GetOrdersByDate(String orderDate) throws FlooringPersistenceException
    {
        //list of orders by date
        Map<Integer, Orders> allOrders = myDao.GetOrdersByDate(orderDate);
        
        return allOrders;
    }
    
    /**
     * GetProducts - loads the file for Products and returns it as a HashMap. It
     * loops through the map to format it to be used in the view
     * -------------------------------------------------------------------------
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<String, String> GetProducts() throws FlooringPersistenceException
    {
        String stringProducts;
        String[] productsArr;
        Products products;
        Map<String, String> productsList = new HashMap<>();
        
        myDao = new FlooringDaoFileImpl("Products.txt"); //gets the list of products
        Map<String, Products> getProducts = myDao.GetProducts();
        
        for(String productKey : getProducts.keySet())
        {
            products = getProducts.get(productKey);
            stringProducts = products.toString();
            productsArr = stringProducts.split("/");
            productsList.put(productKey, productsArr[1] + "/" + productsArr[2]);
        }
        
        return productsList;
    }
    
    /**
     * GetStateTaxes - loads the file for StateTaxes and returns it as a HashMap. It
     * loops through the map to format it to be used in the view
     * -------------------------------------------------------------------------
     * @return
     * @throws FlooringPersistenceException 
     */
    @Override
    public Map<String, String> GetStateTaxes() throws FlooringPersistenceException
    {
        String stringStates;
        String[] statesArr;
        StateTaxes states;
        Map<String, String> statesList = new HashMap<>();
        
        myDao = new FlooringDaoFileImpl("Taxes.txt");
        Map<String, StateTaxes> getStates = myDao.GetStateTaxes(); //get the list of states
        
        for(String stateKey : getStates.keySet())
        {
            states = getStates.get(stateKey);
            stringStates = states.toString();
            statesArr = stringStates.split("/");
            statesList.put(stateKey, statesArr[1] + "/" + statesArr[2]); //assign it to be state abbreviation, state name/tax rate
        }
        
        return statesList;
    }
    
    @Override
    public Orders OrderInfo(Map<Integer, Orders> order, int orderID) throws FlooringPersistenceException
    {
        String[] orderArr;
        Orders orderInfo = null;
        
        for(Integer orderKey : order.keySet())
        {
            if(orderKey == orderID)
            {
                orderArr = order.get(orderKey).toString().split("/");
                
                orderInfo = new Orders(orderID);
                orderInfo.setCustomerName(orderArr[1]);
                orderInfo.setProductType(orderArr[2]);
                orderInfo.setState(orderArr[3]);
                orderInfo.setArea(orderArr[4]);
                orderInfo.setCostPerSqFoot(orderArr[5]);
                orderInfo.setLaborCost(orderArr[6]);
                orderInfo.setLaborCostPerSqFoot(orderArr[7]);
                orderInfo.setMaterialCost(orderArr[8]);
                orderInfo.setStateTaxRate(orderArr[9]);
                orderInfo.setCalcTax(orderArr[10]);
                orderInfo.setTotal(orderArr[11]);
            }
        }
        
        return orderInfo;
    }
}