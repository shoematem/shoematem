/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.repositories.TypeRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class TypeServiceLayerImpl implements TypeServiceLayer{
    
    @Autowired
    TypeRepo types;

    @Override
    public Type getById(int id) {
        Type gotType = types.getOne(id);
        return gotType;
    }

    @Override
    public List<Type> getAllTypes() {
        return types.findAll();
    }
}
