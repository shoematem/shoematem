/**
 * FlooringDao - interface for the dao
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.DAO;

import Flooring.DTO.Orders;
import Flooring.DTO.Products;
import Flooring.DTO.StateTaxes;
import java.util.Map;

public interface FlooringDao
{
    void CreateOrder(Orders order, String orderDate) throws FlooringPersistenceException;
    Orders RemoveOrder(int orderID, String orderDate) throws FlooringPersistenceException;
    Orders EditOrder(int orderID, Orders order, String orderDate) throws FlooringPersistenceException;
    void ExportAllOrders(boolean isExport) throws FlooringPersistenceException;
    Map<Integer, Orders> GetOrdersByDate(String orderDate) throws FlooringPersistenceException;
    Map<String, Products> GetProducts() throws FlooringPersistenceException;
    Map<String, StateTaxes> GetStateTaxes() throws FlooringPersistenceException;
}