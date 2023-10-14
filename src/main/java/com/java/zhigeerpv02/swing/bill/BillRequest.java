package com.java.zhigeerpv02.swing.bill;

import com.alibaba.fastjson.JSON;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.utils.RestTemplateUtil;

import java.util.ArrayList;
import java.util.List;

public class BillRequest {
    private static BillRequest billRequest;

    public static BillRequest getBillRequest() {
        if (billRequest == null) {
            return new BillRequest();
        }
        return billRequest;
    }

    public List<Bill> getList() {
        List customerList = RestTemplateUtil.getRestTemplate().getForObject(RestTemplateUtil.URL + "/bill/findAll", List.class);
        String strList = JSON.toJSONString(customerList, Bill.class.getModifiers());
        List<Bill> bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public void addBill(Bill bill) {

    }

    public void updateCustomer(Bill bill) {
    }

    public void removeBills(List<Integer> ids) {
    }

    public List<Bill> getListByName(String name) {
        return new ArrayList<>();
    }

    public void exportData() {
    }

    public List<Bill> getListByYear(String year) {
        return null;
    }
}
