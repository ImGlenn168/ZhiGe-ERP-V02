package com.java.zhigeerpv02.controller;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.service.BillService;
import com.java.zhigeerpv02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bill/findAll")
    public Result findAll() {
        return Result.success(billService.findAllBills());
    }

    @PostMapping("/bill/addBill")
    public Result addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @PostMapping("/bill/updateBill")
    public Result updateBill(@RequestBody Bill bill) {
        return billService.updateBill(bill);
    }
}
