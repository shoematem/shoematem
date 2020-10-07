/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Type;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface TypeServiceLayer {
    Type getById(int id);
    List<Type> getAllTypes();
}
