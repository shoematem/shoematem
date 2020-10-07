/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.User;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface UserServiceLayer {
    User getById(int id);
    List<User> getAllUsers();
    User addUser(User user);
    User editUser(User user);
    void deleteUser(User user);
}
