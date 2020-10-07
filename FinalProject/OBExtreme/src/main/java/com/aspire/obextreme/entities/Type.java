/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sean5
 */
@Entity(name = "tradetype")
public class Type {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "tradetypeid")
    private int typeId;
    
    @NotNull(message = "type must not be null")
    @Size(min = 1, max = 45, message = "type must be less than 45 characters")
    @Column(name = "typename")
    private String type;
    
    // getters and setters

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    // equals and hashcode

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.typeId;
        hash = 53 * hash + Objects.hashCode(this.type);
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
        final Type other = (Type) obj;
        if (this.typeId != other.typeId) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    
}
