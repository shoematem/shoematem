/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.repositories;

import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean5
 */
@Repository
public interface SellRepo extends JpaRepository<SellOrder, Integer>{
    List<SellOrder> findByStockAndOutstandingGreaterThan(Stock stock, int num);
    List<SellOrder> findByType(Type type);
    List<SellOrder> findByStock(Stock stock);
    List<SellOrder> findByUser(User user);
    List<SellOrder> findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(Stock stock, Type type, int num);
}
