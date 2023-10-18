package com.java.zhigeerpv02.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @ExcelProperty("编号")
    private String bid;
    @ExcelProperty("客户名")
    private String cname;
    @ExcelProperty("下单时间")
    private String orderTime;
    @ExcelProperty("数量")
    private String quantity;
    @ExcelProperty("单价")
    private String unitPrice;
    @ExcelProperty("总价")
    private String totalPrice;
    @ExcelProperty("录入时间")
    private String createTime;

    @ExcelProperty("年份")
    private String year;
    @ExcelProperty("备注")
    private String note;

    public Bill(String bid, String cname, String orderTime, String quantity, String unitPrice, String note) {
        this.bid = bid;
        this.cname = cname;
        this.orderTime = orderTime;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    public Bill(String cname, String orderTime, String quantity, String unitPrice, String note) {
        this.cname = cname;
        this.orderTime = orderTime;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }
}
