/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.service.impl;

import java.util.List;
import org.sbx.customers.dao.CustomerDAO;
import org.sbx.customers.entity.Customer;
import org.sbx.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author loginov_a_s
 */
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    @Qualifier("customerService")
    private CustomerDAO customerDAO;
    
    @Override
    public List<Customer> findAllCustomers() {
        return customerDAO.getAllCustomers();
    }
    
}
