/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.dao;

import java.util.List;
import org.sbx.customers.entity.Customer;

/**
 *
 * @author loginov_a_s
 */
public interface CustomerDAO {
    
    List<Customer> getAllCustomers();
    
}
