package com.aspire.orderbook.repositories;

import com.aspire.orderbook.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean5
 */
@Repository
public interface StockRepo extends JpaRepository<Stock, Integer>{
    
}
