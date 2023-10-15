package com.java.zhigeerpv02.swing.bill;

import com.alibaba.fastjson.JSON;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import com.java.zhigeerpv02.utils.RestTemplateUtil;
import com.java.zhigeerpv02.utils.Result;

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
        Result result = RestTemplateUtil.getRestTemplate().getForObject(RestTemplateUtil.URL + "/bill/findAll", Result.class);
        List<Bill> bills = new ArrayList<>();
        if (result.getData() == null) {
            return bills;
        }
        String strList = JSON.toJSONString(result.getData());
        bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public void addBill(Bill bill) {
        Result result = RestTemplateUtil.getRestTemplate().postForObject(RestTemplateUtil.URL + "/bill/addBill", bill, Result.class);
        if (result.getData() != null) {
            new MsgFrame(result.getData().toString());
        }
        if (result.getCode() > 0) {
            new MsgFrame("添加成功！");
        } else {
            new MsgFrame("添加失败！");
        }
    }

    public void updateBill(Bill bill) {
        Result result = RestTemplateUtil.getRestTemplate().postForObject(RestTemplateUtil.URL + "/bill/updateBill", bill, Result.class);
        if (result.getData() != null) {
            new MsgFrame(result.getData().toString());
        }
        if (result.getCode() > 0) {
            new MsgFrame("修改成功！");
        } else {
            new MsgFrame("修改失败！");
        }
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
