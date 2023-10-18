package com.java.zhigeerpv02.dao.bill;


import com.java.zhigeerpv02.entity.Bill;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@SuppressWarnings("unused")
public class BillSqlProvider {

    private String sql;

    public String removeBills(int id) {
        sql = "delete from bill where bid = '" + id + "'";
        return sql;
    }

    public String addBill(Bill bill) {
        StringBuffer sql1 = new StringBuffer();
        sql1.append("insert into bill(cname,orderTime,quantity,unitPrice,totalPrice,year,note) values( ");
        if (!StringUtils.isBlank(bill.getCname())) {
            sql1.append("'" + bill.getCname() + "'");
        }
        if (!StringUtils.isBlank(bill.getOrderTime())) {
            sql1.append(", '" + bill.getOrderTime() + "'");
        }
        if (!StringUtils.isBlank(bill.getQuantity())) {
            sql1.append(", '" + bill.getQuantity() + "'");
        }
        if (!StringUtils.isBlank(bill.getUnitPrice())) {
            sql1.append(", '" + bill.getUnitPrice() + "'");
        }
        if (!StringUtils.isBlank(bill.getTotalPrice())) {
            sql1.append(", '" + bill.getTotalPrice() + "'");
        }
        if (!StringUtils.isBlank(bill.getYear())) {
            sql1.append(", '" + bill.getYear() + "'");
        }
        if (!StringUtils.isBlank(bill.getNote())) {
            sql1.append(", '" + bill.getNote() + "'");
        } else {
            sql1.append(", '" + "'");
        }
        sql1.append(") ;");
        return sql1.toString();
    }

    public String updateBill(Bill bill) {
        StringBuffer sql1 = new StringBuffer();
        sql1.append("update bill set ");
        if (!StringUtils.isBlank(bill.getBid())) {
            sql1.append("bid = '" + bill.getBid() + "'");
        }
        if (!StringUtils.isBlank(bill.getCname())) {
            sql1.append(", cname = '" + bill.getCname() + "'");
        }
        if (!StringUtils.isBlank(bill.getOrderTime())) {
            sql1.append(", orderTime = '" + bill.getOrderTime() + "'");
        }
        if (!StringUtils.isBlank(bill.getQuantity())) {
            sql1.append(", quantity = '" + bill.getQuantity() + "'");
        }
        if (!StringUtils.isBlank(bill.getUnitPrice())) {
            sql1.append(", unitPrice = '" + bill.getUnitPrice() + "'");
        }
        if (!StringUtils.isBlank(bill.getTotalPrice())) {
            sql1.append(", totalPrice = '" + bill.getTotalPrice() + "'");
        }
        if (!StringUtils.isBlank(bill.getYear())) {
            sql1.append(", year = '" + bill.getYear() + "'");
        }
        sql1.append(", createtime = now()");
        if (!StringUtils.isBlank(bill.getNote())) {
            sql1.append(", note = '" + bill.getNote() + "'");
        }
        sql1.append(" where bid = '" + bill.getBid() + "' ;");
        return sql1.toString();
    }


}
