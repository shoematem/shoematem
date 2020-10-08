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
@Entity
public class User {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "userid")
    private int userId;
    
    @NotNull(message = "MPID must not be null")
    @Size(min = 1, max = 45, message = "MPID must be less than 45 characters")
    @Column(name = "mpid")
    private String marketParticipant;
    
    @NotNull(message = "name must not be null")
    @Size(min = 1, max = 45, message = "name must be less than 45 characters")
    @Column(name = "name")
    private String name;
    
    @NotNull(message = "Username must not be null")
    @Size(min = 1, max = 45, message = "Username must be less than 45 characters")
    @Column(name = "username")
    private String username;
    
    @NotNull(message = "Password must not be null")
    @Size(min = 1, max = 45, message = "Password must be less than 45 characters")
    @Column(name = "password")
    private String password;
    
    @NotNull(message = "administrator feild must not be null")
    @Column(name = "admin")
    private boolean admin;
    
    @NotNull(message = "active feild must not be null")
    @Column(name = "active")
    private boolean active;
    
    // getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMarketParticipant() {
        return marketParticipant;
    }

    public void setMarketParticipant(String marketParticipant) {
        this.marketParticipant = marketParticipant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.userId;
        hash = 79 * hash + Objects.hashCode(this.marketParticipant);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + (this.admin ? 1 : 0);
        hash = 79 * hash + (this.active ? 1 : 0);
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.marketParticipant, other.marketParticipant)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
}
