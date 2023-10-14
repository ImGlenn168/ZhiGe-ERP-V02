package com.java.zhigeerpv02.swing.util;


import com.java.zhigeerpv02.entity.Customer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomerTableData {
    public static DefaultTableModel getModel(List<Customer> customers) {
        String[] columnNames = new String[]{"编号", "姓名", "电话", "微信","备注"};
        System.out.println(customers);
        List<Customer> collect = new ArrayList<>(customers);
        String[][] data = new String[collect.size()][columnNames.length];
        for (int i = 0; i < customers.size(); i++) {
            data[i][0] = collect.get(i).getCid();
            data[i][1] = collect.get(i).getCname();
            data[i][2] = collect.get(i).getPhoneNumber();
            data[i][3] = collect.get(i).getWeChat();
            data[i][4] = collect.get(i).getNote();
        }

        return new DefaultTableModel(data, columnNames);
    }
}
