/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.repositories;

import com.aspire.obextreme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean5
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    
}
