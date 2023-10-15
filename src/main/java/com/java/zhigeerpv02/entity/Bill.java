package com.java.zhigeerpv02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    // 唯一标识
    private String bid;
    // 客户名称
    private String cname;
    // 下单时间
    private String orderTime;
    // 数量
    private String quantity;
    // 单价
    private String unitPrice;
    // 总价=单价*数量
    private String totalPrice;
    // 录入时间
    private String createTime;
    // 年份
    private String year;
    // 备注
    private String note;

    public Bill(String bid, String cname, String orderTime, String quantity, String unitPrice, String note) {
        this.bid=bid;
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
