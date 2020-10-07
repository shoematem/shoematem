/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.repositories;

import com.aspire.obextreme.entities.BuyOrder;
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
public interface BuyRepo extends JpaRepository<BuyOrder, Integer>{
    List<BuyOrder> findByStockAndOutstandingGreaterThan(Stock stock, int num);
    List<BuyOrder> findByType(Type type);
    List<BuyOrder> findByStock(Stock stock);
    List<BuyOrder> findByUser(User user);
    List<BuyOrder> findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(Stock stock, Type type, int num);
}
