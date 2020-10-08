package com.aspire.orderbook.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author sean5
 */
@Entity
public class Trade {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "tradeid")
    private int tradeId;
    
    @NotNull(message = "trade execution time must not be null")
    @Past(message = "execution time must not be in the future")
    private LocalDateTime time;
    
    @NotNull(message = "trade price must not be null")
    @Digits(integer = 4, fraction = 2, message = "trade execution price must be between 0.00 and 9999.99")
    private BigDecimal price;
    
    @NotNull(message = "buy order quantity must not be blank")
    @Column(nullable = false, name = "number")
    private int number;
    
    @NotNull(message = "there must be a buy order for a trade")
    @ManyToOne
    @JoinColumn(name = "buy_order_orderid", nullable = false)
    private BuyOrder buyOrder;
    
    @NotNull(message = "there must be a sell order for a trade")
    @ManyToOne
    @JoinColumn(name = "sell_order_orderid", nullable = false)
    private SellOrder sellOrder;

    // getters and setters

    public int getTradeId() {  return tradeId;  }
    public LocalDateTime getTime() {  return time;  }
    public BigDecimal getPrice() {  return price;  }
    public int getNumber() {  return number;  }
    public BuyOrder getBuyOrder() {  return buyOrder;  }
    public SellOrder getSellOrder() {  return sellOrder;  }

    public void setTradeId(int tradeId) {  this.tradeId = tradeId;  }
    public void setTime(LocalDateTime time) {  this.time = time;  }
    public void setPrice(BigDecimal price) {  this.price = price;  }
    public void setNumber(int num) {  this.number = num;  }
    public void setBuyOrder(BuyOrder buy) {  this.buyOrder = buy;  }
    public void setSellOrder(SellOrder sellOrder) {  this.sellOrder = sellOrder;  }
    

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Trade)) {
            return false;
        }
        Trade trade = (Trade) o;
        return tradeId == trade.tradeId && Objects.equals(time, trade.time) && Objects.equals(price, trade.price) && number == trade.number && Objects.equals(buyOrder, trade.buyOrder) && Objects.equals(sellOrder, trade.sellOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, time, price, number, buyOrder, sellOrder);
    }

    
}
