package com.aspire.orderbook.repositories;

import java.util.List;

import com.aspire.orderbook.entities.SellOrder;
import com.aspire.orderbook.entities.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean5
 */
@Repository
public interface SellOrderRepo extends JpaRepository<SellOrder, Integer>{
    List findByStock(Stock stock);
}
