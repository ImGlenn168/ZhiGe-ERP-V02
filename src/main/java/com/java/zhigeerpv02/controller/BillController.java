package com.java.zhigeerpv02.controller;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.service.BillService;
import com.java.zhigeerpv02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/bill/getByYear")
    public Result getByYear(@RequestBody String year) {
        return billService.getByYear(year);
    }

    @PostMapping("/bill/getByName")
    public Result getByName(@RequestBody String name) {
        return billService.getByName(name);
    }

    @GetMapping("/bill/getTotalPrice")
    public Result getByName() {
        return billService.getTotalPrice();
    }

    @PostMapping("/bill/getTotalPriceByYear")
    public Result getTotalPriceByYear(@RequestBody String year) {
        return billService.getTotalPriceByYear(year);
    }

    @GetMapping(value = "/bill/getTotalPriceByName")
    public Result getTotalPriceByName(@RequestParam(value = "name") String name) {
        return billService.getTotalPriceByName(name);
    }

    @PostMapping("/bill/remove")
    public Result removeCustomers(@RequestBody List<Integer> ids) {
        return billService.removeBills(ids);
    }

    @GetMapping("/bill/export")
    public void exportBills() {
        billService.exportBills();
    }

    @GetMapping("/bill/getBillsByOrderTimeAndName")
    public Result getBillsByOrderTimeAndName(@RequestParam(value = "startDay") String startDay,
                                             @RequestParam(value = "endDay") String endDay,
                                             @RequestParam(value = "name") String name) {
        return billService.getBillsByOrderTimeAndName(startDay, endDay, name);
    }

    @GetMapping("/bill/exportByOrderTimeAndName")
    public void exportByOrderTimeAndName(@RequestParam(value = "startDay") String startDay,
                                         @RequestParam(value = "endDay") String endDay,
                                         @RequestParam(value = "name") String name) {
        billService.exportByOrderTimeAndName(startDay, endDay, name);
    }
}
