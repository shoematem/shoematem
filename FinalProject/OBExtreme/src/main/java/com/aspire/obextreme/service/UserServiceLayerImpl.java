/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.User;
import com.aspire.obextreme.repositories.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class UserServiceLayerImpl implements UserServiceLayer{
    
    @Autowired
    UserRepo users;

    @Override
    public User getById(int id) {
         return users.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return users.findAll();
    }

    @Override
    public User addUser(User user) {
        return users.save(user);
    }

    @Override
    public User editUser(User user) {
        return users.save(user);
    }

    @Override
    public void deleteUser(User user) {
        users.delete(user);
    }
    
}
