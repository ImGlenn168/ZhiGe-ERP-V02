package com.java.zhigeerpv02.swing.bill;

import com.alibaba.fastjson.JSON;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.entity.Customer;
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

    public List<Customer> getList() {
        List customerList = RestTemplateUtil.getRestTemplate().getForObject(RestTemplateUtil.URL + "/customer/findAll", List.class);
        String strList = JSON.toJSONString(customerList, Customer.class.getModifiers());
        List<Customer> customers = JSON.parseArray(strList, Customer.class);
        return customers;
    }

    public void addBill(Bill bill) {
    }

    public void updateCustomer(Bill bill) {
    }

    public void removeBills(List<Integer> ids) {
    }

    public List<Customer> getListByName(String trim) {
        return  new ArrayList<>();
    }

    public void exportData() {
    }
}
