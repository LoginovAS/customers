/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.sbx.customers.dao.CustomerDAO;
import org.sbx.customers.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT customer FROM Customer customers").getResultList();
    }
    
}
