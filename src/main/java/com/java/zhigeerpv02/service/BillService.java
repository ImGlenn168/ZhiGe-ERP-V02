package com.java.zhigeerpv02.service;

import com.java.zhigeerpv02.dao.bill.BillDao;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillDao billDao;

    public List<Bill> findAllBills() {
        return billDao.findAllBills();
    }

    public Result addBill(Bill bill) {
        Result validator = validator(bill);
        if (validator != null) return validator;
        int totalPrice = Integer.parseInt(bill.getQuantity()) * Integer.parseInt(bill.getUnitPrice());
        bill.setTotalPrice(String.valueOf(totalPrice));
        bill.setYear(bill.getOrderTime().substring(0, 4));
        int i = 0;
        try {
            i = billDao.addBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    private static Result validator(Bill bill) {
        if (StringUtils.isBlank(bill.getCname())) {
            return Result.result("客户名不能为空！");
        }
        if (StringUtils.isBlank(bill.getOrderTime())) {
            return Result.result("下单时间不能为空！");
        }
        if (StringUtils.isBlank(bill.getQuantity())) {
            return Result.result("数量不能为空！");
        }
        if (StringUtils.isBlank(bill.getUnitPrice())) {
            return Result.result("单价不能为空！");
        }
        if (!StringUtils.isNumeric(bill.getQuantity()) || !StringUtils.isNumeric(bill.getQuantity())) {
            return Result.result("单价或数量必须为正整数！");
        }
        return null;
    }

    public Result updateBill(Bill bill) {
        Result validator = validator(bill);
        if (validator != null) return validator;
        int totalPrice = Integer.parseInt(bill.getQuantity()) * Integer.parseInt(bill.getUnitPrice());
        bill.setTotalPrice(String.valueOf(totalPrice));
        bill.setYear(bill.getOrderTime().substring(0, 4));
        int i = 0;
        try {
            i = billDao.updateBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }
}
