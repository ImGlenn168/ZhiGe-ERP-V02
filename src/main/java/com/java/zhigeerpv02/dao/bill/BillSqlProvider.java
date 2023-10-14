package com.java.zhigeerpv02.dao.bill;


import com.java.zhigeerpv02.entity.Bill;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("unused")
public class BillSqlProvider {

    private String sql;

    public String addBill(Bill bill) {
        StringBuffer sql1 = new StringBuffer();
        sql1.append("insert into bill() values( ");

        if (!StringUtils.isBlank(bill.getCname())){
            sql1.append("");
        }

        return sql1.toString();
    }

}
