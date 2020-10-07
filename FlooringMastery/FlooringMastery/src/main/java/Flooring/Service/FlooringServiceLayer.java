/**
 * FlooringServiceLayer - interface for the service layer
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.Service;

import Flooring.DAO.FlooringPersistenceException;
import Flooring.DTO.Orders;
import java.util.Map;

public interface FlooringServiceLayer
{
    void CreateOrder(Orders order, String orderDate) throws FlooringPersistenceException;
    Orders RemoveOrder(int orderID, String orderDate) throws FlooringPersistenceException;
    void EditOrder(int orderID, Orders order, String orderDate) throws FlooringPersistenceException;
    void ExportAllOrders() throws FlooringPersistenceException;
    Map<Integer, Orders> GetOrdersByDate(String orderDate) throws FlooringPersistenceException;
    Map<String, String> GetProducts() throws FlooringPersistenceException;
    Map<String, String> GetStateTaxes() throws FlooringPersistenceException;
    Orders CalculateInputs(Orders order) throws FlooringPersistenceException;
    Orders OrderInfo(Map<Integer, Orders> order, int orderID) throws FlooringPersistenceException;
}