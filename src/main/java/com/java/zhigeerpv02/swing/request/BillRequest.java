package com.java.zhigeerpv02.swing.request;

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
        Result result = RestTemplateUtil.getRestTemplate()
                .getForObject(RestTemplateUtil.URL + "/bill/findAll", Result.class);
        List<Bill> bills = new ArrayList<>();
        if (result.getData() == null) {
            return bills;
        }
        String strList = JSON.toJSONString(result.getData());
        bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public void addBill(Bill bill) {
        Result result = RestTemplateUtil.getRestTemplate()
                .postForObject(RestTemplateUtil.URL + "/bill/addBill", bill, Result.class);
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
        Result result = RestTemplateUtil.getRestTemplate()
                .postForObject(RestTemplateUtil.URL + "/bill/updateBill", bill, Result.class);
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
        Result result = RestTemplateUtil.getRestTemplate().postForEntity(RestTemplateUtil.URL + "/bill/remove", ids, Result.class).getBody();
        System.out.println(result);
        int count = (int) result.getData();
        if (count > 0) {
            new MsgFrame("成功删除了" + count + "条数据！");
        } else {
            new MsgFrame("删除失败");
        }
    }

    public void exportData() {
        RestTemplateUtil.getRestTemplate().getForEntity(RestTemplateUtil.URL + "/bill/export", Void.class);
        new MsgFrame("文件已存入D:/账单信息.xlsx");
    }

    public List<Bill> getListByYear(String year) {
        Result result = RestTemplateUtil.getRestTemplate()
                .postForObject(RestTemplateUtil.URL + "/bill/getByYear", year, Result.class);
        List<Bill> bills = new ArrayList<>();
        if (result.getData() == null) {
            return bills;
        }
        String strList = JSON.toJSONString(result.getData());
        bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public List<Bill> getListByName(String name) {
        Result result = RestTemplateUtil.getRestTemplate()
                .postForObject(RestTemplateUtil.URL + "/bill/getByName", name, Result.class);
        List<Bill> bills = new ArrayList<>();
        if (result.getData() == null) {
            return bills;
        }
        String strList = JSON.toJSONString(result.getData());
        bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public String getTotalPrice() {
        Result result = RestTemplateUtil.getRestTemplate()
                .getForObject(RestTemplateUtil.URL + "/bill/getTotalPrice", Result.class);
        if (result.getData() == null) {
            return "";
        }
        return String.valueOf(result.getData());
    }

    public String getTotalPriceByYear(String year) {
        Result result = RestTemplateUtil.getRestTemplate()
                .postForObject(RestTemplateUtil.URL + "/bill/getTotalPriceByYear", year, Result.class);
        if (result.getData() == null) {
            return "";
        }
        return String.valueOf(result.getData());
    }

    public String getTotalPriceByName(String name) {
        Result result = RestTemplateUtil.getRestTemplate()
                .getForObject(RestTemplateUtil.URL + "/bill/getTotalPriceByName?name=" + name, Result.class);
        if (result.getData() == null) {
            return "";
        }
        return String.valueOf(result.getData());
    }

    public List<Bill> getByOrderTimeAndName(String startDay, String endDay, String name) {
        Result result = RestTemplateUtil.getRestTemplate()
                .getForObject(RestTemplateUtil.
                        URL + "/bill/getBillsByOrderTimeAndName?startDay=" + startDay + "&endDay=" + endDay + "&name=" + name, Result.class);
        List<Bill> bills = new ArrayList<>();
        if (result.getData() == null) {
            return bills;
        }
        String strList = JSON.toJSONString(result.getData());
        bills = JSON.parseArray(strList, Bill.class);
        return bills;
    }

    public void exportBillsByOrderTimeAndName(String startDay, String endDay, String name) {
        RestTemplateUtil.getRestTemplate()
                .getForEntity(RestTemplateUtil.URL + "/bill/exportByOrderTimeAndName?startDay=" + startDay + "&endDay=" + endDay + "&name=" + name, Void.class);
        new MsgFrame("文件已存入D:/" + startDay + "-" + endDay + name + "账单信息.xlsx");
    }
}
