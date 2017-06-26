/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.jsfbeans;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.sbx.customers.entity.Customer;
import org.sbx.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@ViewScoped
public class CustomerBean {
    @Autowired
    private CustomerService customerService;

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    @PostConstruct
    public void setup() {
        this.customers = customerService.findAllCustomers();
    }
}
