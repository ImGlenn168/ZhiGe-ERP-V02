package com.java.zhigeerpv02.dao.customer;

import com.java.zhigeerpv02.entity.Customer;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("unused")
public class CustomerSqlProvider {
    private String sql = "";

    public String findCustomerByName(String cname) {
        sql = "select * from customer where cname like " + "'%" + cname + "%'" + " ;";
        return sql;
    }


    public String addCustomer(Customer customer) {
        StringBuffer sql1 = new StringBuffer();
        sql1.append("insert into customer(`cid`, `cname`, `phoneNumber`, `weChat`, `note`) values ( ");
        if (!StringUtils.isBlank(customer.getCid())) {
            sql1.append("'"+customer.getCid()+"'");
        }
        if (!StringUtils.isBlank(customer.getCname())) {
            sql1.append(", '"+customer.getCname()+"'");
        }
        if (!StringUtils.isBlank(customer.getPhoneNumber())) {
            sql1.append(", '"+customer.getPhoneNumber()+"'");
        }
        if (!StringUtils.isBlank(customer.getWeChat())) {
            sql1.append(", '"+customer.getWeChat()+"'");
        }
        if (!StringUtils.isBlank(customer.getNote())) {
            sql1.append(", '"+customer.getNote()+"'");
        }
        sql1.append(" ) ;");
        return sql1.toString();
    }

    public String removeCustomer(int id) {
        sql = "delete from customer where cid = '" + id + "' ;";
        return sql;
    }

    public String updateCustomer(Customer customer) {
        StringBuffer sql = new StringBuffer();
        sql.append("update customer set ");
        if (!StringUtils.isBlank(customer.getCid())) {
            sql.append("cid= '" + customer.getCid() + "'");
        }
        if (!StringUtils.isBlank(customer.getCname())) {
            sql.append(", cname= '" + customer.getCname() + "'");
        }
        if (!StringUtils.isBlank(customer.getPhoneNumber())) {
            sql.append(", phoneNumber= '" + customer.getPhoneNumber() + "'");
        }
        if (!StringUtils.isBlank(customer.getWeChat())) {
            sql.append(", weChat= '" + customer.getWeChat() + "'");
        }
        if (!StringUtils.isBlank(customer.getNote())) {
            sql.append(", note= '" + customer.getNote() + "'");
        }
        sql.append("where cid= '" + customer.getCid() + "' ;");
        return sql.toString();
    }


}
