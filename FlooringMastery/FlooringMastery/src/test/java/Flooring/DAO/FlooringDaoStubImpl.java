/**
 * FlooringDaoStubImpl
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 08/01/2020
 */
package Flooring.DAO;

import Flooring.DTO.Orders;
import Flooring.DTO.Products;
import Flooring.DTO.StateTaxes;
import java.util.HashMap;
import java.util.Map;

public class FlooringDaoStubImpl implements FlooringDao
{
    public Orders onlyOrder;
    
    public FlooringDaoStubImpl()
    {
        onlyOrder = new Orders(1);
        onlyOrder.setCustomerName("Matthew Shoemate");
        onlyOrder.setState("WA");
        onlyOrder.setProductType("Tile");
        onlyOrder.setArea("150");
    }
    
    public FlooringDaoStubImpl(Orders testOrder)
    {
        this.onlyOrder = testOrder;
    }
    
    @Override
    public void CreateOrder(Orders order, String orderDate) throws FlooringPersistenceException
    {

    }
    
    @Override
    public Map<Integer, Orders> GetOrdersByDate(String orderDate) throws FlooringPersistenceException
    {
        Map<Integer, Orders> ordersList = new HashMap<>();
        ordersList.put(onlyOrder.getOrderID(), onlyOrder);
        
        return ordersList;
    }
    
    @Override
    public Orders RemoveOrder(int orderID, String orderDate) throws FlooringPersistenceException
    {
        if(orderID == onlyOrder.getOrderID())
        {
            return onlyOrder;
        } else
        {
            return null;
        }
    }
    
    @Override
    public void ExportAllOrders(boolean isExport) throws FlooringPersistenceException
    {}
    
    @Override
    public Orders EditOrder(int orderID, Orders order, String orderDate) throws FlooringPersistenceException
    {
        return order;
    }
    
    @Override
    public Map<String, Products> GetProducts() throws FlooringPersistenceException
    {
        Map<String, Products> product = new HashMap<>();
        return product;
    }
    
    @Override
    public Map<String, StateTaxes> GetStateTaxes() throws FlooringPersistenceException
    {
        Map<String, StateTaxes> states = new HashMap<>();
        return states;
    }
}