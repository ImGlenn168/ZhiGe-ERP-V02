package com.java.zhigeerpv02.swing.bill;


import com.java.zhigeerpv02.entity.Bill;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class BillTableData {
    public static DefaultTableModel getModel(List<Bill> bills) {
        String[] columnNames = new String[]{"编号", "客户名", "下单时间", "数量","单价","总价","录入时间","年份","备注"};
        List<Bill> collect = new ArrayList<>(bills);
        String[][] data = new String[collect.size()][columnNames.length];
        for (int i = 0; i < bills.size(); i++) {
            data[i][0] = collect.get(i).getBid();
            data[i][1] = collect.get(i).getCname();
            data[i][2] = collect.get(i).getOrderTime();
            data[i][3] = collect.get(i).getQuantity();
            data[i][4] = collect.get(i).getUnitPrice();
            data[i][5] = collect.get(i).getTotalPrice();
            data[i][6] = collect.get(i).getCreateTime();
            data[i][7] = collect.get(i).getYear();
            data[i][8] = collect.get(i).getNote();
        }

        return new DefaultTableModel(data, columnNames);
    }
}
