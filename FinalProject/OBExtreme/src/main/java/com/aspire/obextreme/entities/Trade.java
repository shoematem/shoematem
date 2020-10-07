/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.entities;

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
@Entity(name = "trade")
public class Trade {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "tradeid")
    private int tradeId;
    
    @NotNull(message = "trade execution time must not be null")
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

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BuyOrder getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(BuyOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public SellOrder getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(SellOrder sellOrder) {
        this.sellOrder = sellOrder;
    }
    
    // equals and hashcode

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.tradeId;
        hash = 17 * hash + Objects.hashCode(this.time);
        hash = 17 * hash + Objects.hashCode(this.price);
        hash = 17 * hash + this.number;
        hash = 17 * hash + Objects.hashCode(this.buyOrder);
        hash = 17 * hash + Objects.hashCode(this.sellOrder);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trade other = (Trade) obj;
        if (this.tradeId != other.tradeId) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.buyOrder, other.buyOrder)) {
            return false;
        }
        if (!Objects.equals(this.sellOrder, other.sellOrder)) {
            return false;
        }
        return true;
    }
    
    
}
