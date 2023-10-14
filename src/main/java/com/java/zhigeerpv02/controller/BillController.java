package com.java.zhigeerpv02.controller;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bill/findAll")
    public List<Bill> findAll() {
        return billService.findAllBills();
    }

    
}
