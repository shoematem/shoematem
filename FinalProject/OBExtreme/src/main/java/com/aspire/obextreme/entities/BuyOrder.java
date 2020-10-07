/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.entities;

import java.math.BigDecimal;
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

/**
 *
 * @author sean5
 */
@Entity(name = "buy_order")
public class BuyOrder {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "orderid")
    private int orderId;
    
    @Digits(integer = 5, fraction = 0, message = "the order number should be between 1 and 99999")
    @NotNull(message = "there must be an order number entered")
    @Column(name = "number", nullable = false)
    private int number;
    
    @Digits(integer = 4, fraction = 2, message = "the price must be between 0 and 9999.99")
    @Column(name = "price")
    private BigDecimal price;
    
    @NotNull(message = "the outstanding order must not be null")
    @Column(name = "outstanding", nullable = false)
    private int outstanding;
    
    @NotNull(message = "there must be a stock for a buy order")
    @ManyToOne
    @JoinColumn(name = "stock_stockid", nullable = false)
    private Stock stock;
    
    @NotNull(message = "there must be a user for a buy order")
    @ManyToOne
    @JoinColumn(name = "user_userid", nullable = false)
    private User user;
    
    @NotNull(message = "there must be a type for a buy order")
    @ManyToOne
    @JoinColumn(name = "tradetype_tradetypeid", nullable = false)
    private Type type;
    
    
    // getters and setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(int outstanding) {
        this.outstanding = outstanding;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    // equals and hashcode

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.orderId;
        hash = 17 * hash + this.number;
        hash = 17 * hash + Objects.hashCode(this.price);
        hash = 17 * hash + this.outstanding;
        hash = 17 * hash + Objects.hashCode(this.stock);
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + Objects.hashCode(this.type);
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
        final BuyOrder other = (BuyOrder) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (this.outstanding != other.outstanding) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    
}
