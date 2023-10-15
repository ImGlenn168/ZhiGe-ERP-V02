package com.java.zhigeerpv02.swing.bill;

import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class BillAddAndUpdateFrame extends JFrame {
    private BillRequest billRequest = BillRequest.getBillRequest();
    private BillFrame billFrame;
    private String title;
    private String confirm;
    private Bill bill;
    private int type;

    private String[] cnames = {"aaa", "bbb", "ccc"};

    JTextField bid, orderTime, quantity, unitPrice, totalPrice, createTime, year;
    JButton add, reset;

    JLabel bidLabel, cnameLabel, orderTimeLabel, quantityLabel,
            unitPriceLabel, totalPriceLabel, createTimeLabel, yearLabel, noteLabel;

    JTextArea note;

    JComboBox cname;

    // 将主面板传入，方便数据传递。 type用于判断是添加数据还是修改数据
    public BillAddAndUpdateFrame(final BillFrame billFrame, int type) throws HeadlessException {
        this.type = type;
        title = "添加客户";
        confirm = "添加";
        this.billFrame = billFrame;
        setBounds(700, 280, 400, 450);
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
        setBounds(700, 280, 400, 450);
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCom();
        this.setVisible(true);
    }

    public void addCom() {

        bidLabel = new JLabel("  编号：");
        bidLabel.setBounds(40, 30, 75, 30);
        bid = new JTextField();
        bid.setEditable(false);
        bid.setBounds(110, 30, 200, 30);

        cnameLabel = new JLabel(" 客户名：");
        cnameLabel.setBounds(40, 70, 75, 30);
        cname = new JComboBox(cnames);
        cname.setBounds(110, 70, 200, 30);

        orderTimeLabel = new JLabel("  下单时间：");
        orderTimeLabel.setBounds(40, 110, 75, 30);
        orderTime = new JTextField();
        orderTime.setBounds(110, 110, 200, 30);

        quantityLabel = new JLabel("  数量：");
        quantityLabel.setBounds(40, 150, 75, 30);
        quantity = new JTextField();
        quantity.setBounds(110, 150, 200, 30);

        unitPriceLabel = new JLabel("  单价：");
        unitPriceLabel.setBounds(40, 190, 75, 30);
        unitPrice = new JTextField();
        unitPrice.setBounds(110, 190, 200, 30);

//        totalPriceLabel = new JLabel("  总价：");
//        totalPriceLabel.setBounds(40, 230, 75, 30);
//        totalPrice = new JTextField();
//        totalPrice.setBounds(110, 230, 200, 30);

//        createTimeLabel = new JLabel("录入时间：");
//        createTimeLabel.setBounds(50, 270, 75, 30);
//        createTime = new JTextField();
//        createTime.setBounds(100, 270, 200, 30);

//        yearLabel = new JLabel("年份：");
//        yearLabel.setBounds(50, 310, 75, 30);
//        year = new JTextField();
//        year.setBounds(100, 310, 200, 30);

        noteLabel = new JLabel("  备注：");
        noteLabel.setBounds(40, 230, 75, 30);
        note = new JTextArea();
        note.setBounds(110, 230, 200, 50);


        add = new JButton(confirm);
        add.setBounds(110, 320, 75, 30);

        reset = new JButton("重置");
        reset.setBounds(210, 320, 75, 30);

        if (type == 2) {
            bid.setEditable(false);
            bid.setText(bill.getBid());
            cname.setSelectedItem(bill.getCname());
            orderTime.setText(bill.getOrderTime());
            quantity.setText(bill.getQuantity());
            unitPrice.setText(bill.getUnitPrice());
//            int quantity = Integer.parseInt(bill.getQuantity());
//            int unitPrice = Integer.parseInt(bill.getUnitPrice());
//            totalPrice.setEditable(false);
//            totalPrice.setText(String.valueOf(quantity * unitPrice));
//            createTime.setText(bill.getCreateTime());
//            year.setEditable(false);
//            year.setText(bill.getOrderTime().substring(0, 4));
            note.setText(bill.getNote());
        }

        addListener();
        add(bid);
        add(cname);
        add(orderTime);
        add(quantity);
        add(unitPrice);
//        add(totalPrice);
//        add(createTime);
//        add(year);
        add(note);
        add(bidLabel);
        add(cnameLabel);
        add(orderTimeLabel);
        add(quantityLabel);
        add(unitPriceLabel);
//        add(totalPriceLabel);
//        add(createTimeLabel);
//        add(yearLabel);
        add(noteLabel);
        add(add);
        add(reset);
    }

    private void addCustomer() {
        if (!validation()) return;
        try {
            bill = new Bill(
//                    bid.getText().trim(),
                    cname.getSelectedItem().toString().trim(), "20" + orderTime.getText().trim(),
                    quantity.getText().trim(), unitPrice.getText().trim(), Optional.of(note.getText().trim()).orElse(""));
            billRequest.addBill(bill);
            billFrame.showData(billRequest.getList());
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            new MsgFrame("添加客户发生异常！");
        }
    }

    private void updateBill() {
        if (!validation()) return;
        bill = new Bill(bid.getText().trim(), cname.getSelectedItem().toString().trim(), orderTime.getText().trim(),
                quantity.getText().trim(), unitPrice.getText().trim(), note.getText().trim());
        billRequest.updateBill(bill);
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
                        updateBill();
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
                cname.setName("");
                orderTime.setText("");
                quantity.setText("");
                unitPrice.setText("");
//                totalPrice.setText("");
                createTime.setText("");
                year.setText("");
                note.setText("");
            }
        });
    }

    // 用于验证数据是否为空
    private boolean validation() {
        boolean flag = true;
//        String bid = this.bid.getText().trim();
        String cname = this.cname.getSelectedItem().toString().trim();
        String orderTime = this.orderTime.getText().trim();
        String quantity = this.quantity.getText().trim();
        String unitPrice = this.unitPrice.getText().trim();
//        String totalPrice = this.totalPrice.getText().trim();
//        String createTime = this.createTime.getText().trim();
//        String year = this.year.getText().trim();
//        String note = this.note.getText().trim();
        if (StringUtils.isBlank(cname) || StringUtils.isBlank(orderTime) ||
                StringUtils.isBlank(quantity) || StringUtils.isBlank(unitPrice)) {
            flag = false;
            new MsgFrame("属性不能为空！");
            return flag;
        }
//        if (!bid.matches("^[0-9]*$")) {
//            flag = false;
//            new MsgFrame("编号必须为数字！");
//            return flag;
//        }
        return flag;
    }
}
