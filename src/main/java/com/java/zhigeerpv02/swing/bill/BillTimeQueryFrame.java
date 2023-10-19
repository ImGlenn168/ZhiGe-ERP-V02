package com.java.zhigeerpv02.swing.bill;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.swing.request.BillRequest;
import com.java.zhigeerpv02.swing.util.MsgFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BillTimeQueryFrame extends JFrame {
    private BillRequest billRequest = BillRequest.getBillRequest();

    private BillFrame billFrame;
    JLabel startDayLabel, endDayLabel, cname;
    JTextField startDayField, endDayField;
    JComboBox cnameBox;
    JButton confirm, cancel, export;
    String startDay, endDay, name;

    public BillTimeQueryFrame(BillFrame billFrame, String[] names) throws HeadlessException {
        this.billFrame = billFrame;
        setTitle("根据下单时间查询");
        this.setBounds(580, 380, 400, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(MsgFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        addCon(billFrame, names);
        this.setVisible(true);
    }

    private void addCon(BillFrame billFrame, String[] names) {
        startDayLabel = new JLabel("开始日期：");
        startDayLabel.setBounds(50, 30, 75, 30);
        startDayField = new JTextField();
        startDayField.setBounds(120, 30, 200, 30);

        endDayLabel = new JLabel("结束日期：");
        endDayLabel.setBounds(50, 80, 75, 30);
        endDayField = new JTextField();
        endDayField.setBounds(120, 80, 200, 30);

        cname = new JLabel(" 客户名：");
        cname.setBounds(50, 130, 75, 30);
        cnameBox = new JComboBox(names);
        cnameBox.setBounds(120, 130, 200, 30);

        confirm = new JButton("确定");
        confirm.setBounds(120, 210, 100, 30);

        cancel = new JButton("取消");
        cancel.setBounds(250, 210, 100, 30);

        export = new JButton("导出查询结果");
        export.setBounds(120, 260, 120, 30);

        addListener(billFrame);
        this.add(startDayLabel);
        this.add(startDayField);
        this.add(endDayLabel);
        this.add(endDayField);
        this.add(cname);
        this.add(cnameBox);
        this.add(confirm);
        this.add(cancel);
        this.add(export);
    }

    public void addListener(BillFrame billFrame) {
        confirmed(billFrame);
        canceled();
        exportBillsByOrderTimeAndName();
    }

    private void exportBillsByOrderTimeAndName() {
        export.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                billRequest.exportBillsByOrderTimeAndName(startDayField.getText().trim(), endDayField.getText().trim(), cnameBox.getSelectedItem().toString());
            }
        });
    }

    public void confirmed(BillFrame billFrame) {
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Bill> bills = doQuery();
                billFrame.showData(bills);
                billFrame.showTotalPrice(startDay + "至" + endDay + name + "的账单金额: " + getTotalPrice(bills));
            }
        });
    }

    private int getTotalPrice(List<Bill> bills) {
        int totalPrice = 0;
        for (Bill bill : bills) {
            String tp = bill.getTotalPrice();
            int tPrice = Integer.parseInt(tp);
            totalPrice += tPrice;
        }
        return totalPrice;
    }

    /**
     * 下单区间和客户名查询
     */
    private List<Bill> doQuery() {
        startDay = startDayField.getText().trim();
        endDay = endDayField.getText().trim();
        name = cnameBox.getSelectedItem().toString();
        return billRequest.getByOrderTimeAndName(startDay, endDay, name);
    }

    public void canceled() {
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }
}
