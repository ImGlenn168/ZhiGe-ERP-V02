package com.java.zhigeerpv02.swing.util;

import com.java.zhigeerpv02.swing.bill.BillFrame;
import com.java.zhigeerpv02.swing.customer.CustomerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionFrame extends JFrame {

    private CustomerFrame customerFrame;

    private BillFrame billFrame;

    public static OptionFrame optionFrame;

    public static OptionFrame getOptionFrame(){
        if (optionFrame==null){
            optionFrame=new OptionFrame();
        }
        return optionFrame;
    }

    public OptionFrame() throws HeadlessException {
        setTitle("企业管理系统");
        this.setBounds(680, 340, 350, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(OptionFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        JLabel jLabel = new JLabel("请选择要操作的功能");
        jLabel.setBounds(120, 10, 200, 30);
        JButton customerButton = new JButton("客户管理");
        customerButton.setBounds(120, 70, 100, 50);
        JButton billButton = new JButton("账单管理");
        billButton.setBounds(120, 150, 100, 50);
        customerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerFrame = new CustomerFrame();
                dispose();
            }
        });
        billButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                billFrame = new BillFrame();
                dispose();
            }
        });
        this.add(customerButton);
        this.add(billButton);
        this.add(jLabel);
    }
}
