package com.java.zhigeerpv02.controller;

import com.java.zhigeerpv02.entity.Customer;
import com.java.zhigeerpv02.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/findAll")
    public List<Customer> findAll() {
        return customerService.getList();
    }

    @GetMapping("/customer/findByName/{name}")
    public List<Customer> findByName(@PathVariable("name") String name) {
        return customerService.queryCustomers(name);
    }

    @GetMapping("/customer/export")
    public void findByName() {
        try {
            customerService.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/customer/remove")
    public int removeCustomers(@RequestBody List<Integer> ids) {
        int count = 0;
        try {
            count = customerService.removeCustomers(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @PostMapping("/customer/update")
    public int updateCustomer(@RequestBody Customer customer) {
        int i = 0;
        try {
            i = customerService.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @PostMapping("/customer/add")
    public int addCustomer(@RequestBody Customer customer) {
        int i = 0;
        try {
            i = customerService.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
