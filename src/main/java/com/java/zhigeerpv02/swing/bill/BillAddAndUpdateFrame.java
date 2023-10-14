package com.java.zhigeerpv02.swing.bill;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BillAddAndUpdateFrame extends JFrame {
    private BillRequest billRequest = BillRequest.getBillRequest();
    private BillFrame billFrame;
    private String title;
    private String confirm;
    private Bill bill;
    private int type;

    JTextField bid, cname, orderTime, quantity, unitPrice, totalPrice, createTime, year, note;
    JButton add, reset;

    JLabel bidLabel, cnameLabel, orderTimeLabel, quantityLabel,
            unitPriceLabel, totalPriceLabel, createTimeLabel, yearLabel, noteLabel;

    // 将主面板传入，方便数据传递。 type用于判断是添加数据还是修改数据
    public BillAddAndUpdateFrame(final BillFrame billFrame, int type) throws HeadlessException {
        this.type = type;
        title = "添加客户";
        confirm = "添加";
        this.billFrame = billFrame;
        setBounds(700, 280, 350, 350);
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCom();
        this.setVisible(true);
    }

    // 构造方法重载，若传入一个student对象，则表示需要修改此对象。
    public BillAddAndUpdateFrame(final BillFrame billFrame, int type, Bill bill) throws HeadlessException {
        this.bill = bill;
        this.type = type;
        title = "修改学生";
        confirm = "修改";
        this.billFrame = billFrame;
        setBounds(700, 280, 350, 350);
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCom();
        this.setVisible(true);
    }

    public void addCom() {

        bidLabel = new JLabel("编号：");
        bidLabel.setBounds(50, 30, 75, 30);
        bid = new JTextField();
        bid.setBounds(100, 30, 200, 30);

        cnameLabel = new JLabel("客户名：");
        cnameLabel.setBounds(50, 70, 75, 30);
        cname = new JTextField();
        cname.setBounds(100, 70, 200, 30);

        orderTimeLabel = new JLabel("下单时间：");
        orderTimeLabel.setBounds(50, 110, 75, 30);
        orderTime = new JTextField();
        orderTime.setBounds(100, 110, 200, 30);

        quantityLabel = new JLabel("数量：");
        quantityLabel.setBounds(50, 150, 75, 30);
        quantity = new JTextField();
        quantity.setBounds(100, 150, 200, 30);

        unitPriceLabel = new JLabel("单价：");
        unitPriceLabel.setBounds(50, 190, 75, 30);
        unitPrice = new JTextField();
        unitPrice.setBounds(100, 190, 200, 30);

        totalPriceLabel = new JLabel("总价：");
        totalPriceLabel.setBounds(50, 230, 75, 30);
        totalPrice = new JTextField();
        totalPrice.setBounds(100, 230, 200, 30);

//        createTimeLabel = new JLabel("录入时间：");
//        createTimeLabel.setBounds(50, 270, 75, 30);
//        createTime = new JTextField();
//        createTime.setBounds(100, 270, 200, 30);

//        yearLabel = new JLabel("年份：");
//        yearLabel.setBounds(50, 310, 75, 30);
//        year = new JTextField();
//        year.setBounds(100, 310, 200, 30);

        noteLabel = new JLabel("备注：");
        noteLabel.setBounds(50, 350, 75, 30);
        note = new JTextField();
        note.setBounds(100, 350, 200, 30);


        add = new JButton(confirm);
        add.setBounds(100, 240, 75, 30);

        reset = new JButton("重置");
        reset.setBounds(200, 240, 75, 30);

        if (type == 2) {
            bid.setEditable(false);
            bid.setText(bill.getBid());
            cname.setText(bill.getCname());
            orderTime.setText(bill.getOrderTime());
            quantity.setText(bill.getQuantity());
            unitPrice.setText(bill.getUnitPrice());
            int quantity = Integer.parseInt(bill.getQuantity());
            int unitPrice = Integer.parseInt(bill.getUnitPrice());
            totalPrice.setEditable(false);
            totalPrice.setText(String.valueOf(quantity * unitPrice));
//            createTime.setText(bill.getCreateTime());
            year.setEditable(false);
            year.setText(bill.getOrderTime().substring(0, 4));
            note.setText(bill.getNote());
        }
        addListener();
        add(bid);
        add(cname);
        add(orderTime);
        add(quantity);
        add(unitPrice);
        add(totalPrice);
//        add(createTime);
//        add(year);
        add(note);
        add(bidLabel);
        add(cnameLabel);
        add(orderTimeLabel);
        add(quantityLabel);
        add(unitPriceLabel);
        add(totalPriceLabel);
//        add(createTimeLabel);
//        add(yearLabel);
        add(noteLabel);
        add(add);
        add(reset);
    }

    private void addCustomer() {
        if (!validation()) return;
        try {
            bill = new Bill(cname.getText().trim(), orderTime.getText().trim(),
                    quantity.getText().trim(), unitPrice.getText().trim(), totalPrice.getText().trim(),
                    orderTime.getText().trim().substring(0, 4), note.getText().trim());
            System.out.println(bill);
            billRequest.addBill(bill);

            billFrame.showData(billRequest.getList());
            dispose();
        } catch (Exception e) {
            new MsgFrame("添加客户发生异常！");
        }
    }

    private void updateStudent() {
        if (!validation()) return;
        bill = new Bill(cname.getText().trim(), orderTime.getText().trim(),
                quantity.getText().trim(), unitPrice.getText().trim(), totalPrice.getText().trim(),
                orderTime.getText().trim().substring(0, 4), note.getText().trim());
        billRequest.updateCustomer(bill);
        billFrame.showData(billRequest.getList());
        dispose();
    }

    // 为控件添加事件监听
    public void addListener() {
        // 根据type判断添加或是修改
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (type) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        updateStudent();
                        break;
                }
            }
        });
        // 重置输入
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reset();
            }

            private void reset() {
                if (type == 1) {
                    bid.setText("");
                }
                cname.setText("");
                orderTime.setText("");
                quantity.setText("");
                unitPrice.setText("");
                totalPrice.setText("");
                createTime.setText("");
                year.setText("");
                note.setText("");
            }
        });
    }

    // 用于验证数据是否为空
    private boolean validation() {
        boolean flag = true;
        String bid = this.bid.getText().trim();
        String cname = this.cname.getText().trim();
        String orderTime = this.orderTime.getText().trim();
        String quantity = this.quantity.getText().trim();
        String unitPrice = this.unitPrice.getText().trim();
//        String totalPrice = this.totalPrice.getText().trim();
//        String createTime = this.createTime.getText().trim();
//        String year = this.year.getText().trim();
//        String note = this.note.getText().trim();
        if (StringUtils.isBlank(bid) || StringUtils.isBlank(cname) || StringUtils.isBlank(orderTime) ||
                StringUtils.isBlank(quantity) || StringUtils.isBlank(unitPrice)) {
            flag = false;
            new MsgFrame("属性不能为空！");
            return flag;
        }
        if (!bid.matches("^[0-9]*$")) {
            flag = false;
            new MsgFrame("编号必须为数字！");
            return flag;
        }
        return flag;
    }
}
