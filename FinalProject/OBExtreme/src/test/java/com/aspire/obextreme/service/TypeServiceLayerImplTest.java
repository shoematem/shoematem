/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sean5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceLayerImplTest {
    
    @Autowired 
    TypeServiceLayer types;
    
    private final Type type1;
    private final Type type2;
    private final Type type3;
    
    public TypeServiceLayerImplTest() {
        type1 = new Type();
        type1.setType("Market");
        type1.setTypeId(1);
        
        type2 = new Type();
        type2.setTypeId(2);
        type2.setType("Limit");

        type3 = new Type();
        type3.setTypeId(3);
        type3.setType("Stop");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getById method, of class TypeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetById() {
        Type testType1 = types.getById(1);
        Type testType2 = types.getById(2);
        Type testType3 = types.getById(3);
        
        assertEquals(testType1, type1);
        assertEquals(testType2, type2);
        assertEquals(testType3, type3);
    }

    /**
     * Test of getAllTypes method, of class TypeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetAllTypes() {
        List<Type> allTypes = types.getAllTypes();
        
        assertEquals(allTypes.size(), 3);
        assertTrue(allTypes.contains(type1));
        assertTrue(allTypes.contains(type2));
        assertTrue(allTypes.contains(type3));
    }
    
}
