package com.java.zhigeerpv02.swing.customer;

import com.java.zhigeerpv02.entity.Customer;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerAddAndUpdateFrame extends JFrame {
    private CustomerRequest customerRequest = CustomerRequest.getCustomerRequest();
    private CustomerFrame customerFrame;
    private String title;
    private String confirm;
    private Customer customer;
    private int type;

    JTextField cid, cname, tel, weChat, note;
    JButton add, reset;

    JLabel cidLabel, nameLabel, telLabel, weChatLabel, noteLabel;

    // 将主面板传入，方便数据传递。 type用于判断是添加数据还是修改数据
    public CustomerAddAndUpdateFrame(final CustomerFrame customerFrame, int type) throws HeadlessException {
        this.type = type;
        title = "添加客户";
        confirm = "添加";
        this.customerFrame = customerFrame;
        setBounds(700, 280, 350, 350);
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCom();

        this.setVisible(true);
    }

    // 构造方法重载，若传入一个student对象，则表示需要修改此对象。
    public CustomerAddAndUpdateFrame(final CustomerFrame customerFrame, int type, Customer customer) throws HeadlessException {
        this.customer = customer;
        this.type = type;
        title = "修改学生";
        confirm = "修改";
        this.customerFrame = customerFrame;
        setBounds(700, 280, 350, 350);
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCom();
        this.setVisible(true);
    }

    public void addCom() {

        cidLabel = new JLabel("编号：");
        cidLabel.setBounds(50, 30, 75, 30);

        nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(50, 70, 75, 30);

        telLabel = new JLabel("电话：");
        telLabel.setBounds(50, 110, 75, 30);

        weChatLabel = new JLabel("微信：");
        weChatLabel.setBounds(50, 150, 75, 30);

        noteLabel = new JLabel("备注：");
        noteLabel.setBounds(50, 190, 75, 30);

        cid = new JTextField();
        cid.setBounds(100, 30, 200, 30);

        cname = new JTextField();
        cname.setBounds(100, 70, 200, 30);

        tel = new JTextField();
        tel.setBounds(100, 110, 200, 30);

        weChat = new JTextField();
        weChat.setBounds(100, 150, 200, 30);

        note = new JTextField();
        note.setBounds(100, 190, 200, 30);


        add = new JButton(confirm);
        add.setBounds(100, 240, 75, 30);

        reset = new JButton("重置");
        reset.setBounds(200, 240, 75, 30);

        if (type == 2) {
            cid.setEditable(false);
            cid.setText(customer.getCid());
            cname.setText(customer.getCname());
            tel.setText(customer.getPhoneNumber());
            weChat.setText(customer.getWeChat());
            note.setText(customer.getNote());
        }
        addListener();
        add(cid);
        add(cname);
        add(tel);
        add(weChat);
        add(note);
        add(cidLabel);
        add(nameLabel);
        add(telLabel);
        add(weChatLabel);
        add(noteLabel);
        add(add);
        add(reset);
    }

    private void addCustomer() {
        if (!validation()) return;
        try {
            customer = new Customer(cid.getText().trim(), cname.getText().trim(), tel.getText().trim(),
                    weChat.getText().trim(), note.getText().trim());
            System.out.println(customer);
            customerRequest.addCustomer(customer);

            customerFrame.showData(customerRequest.getList());
            dispose();
        } catch (Exception e) {
            new MsgFrame("添加客户发生异常！");
        }
    }

    private void updateStudent() {
        if (!validation()) return;
        customer = new Customer(cid.getText().trim(), cname.getText().trim(),
                tel.getText().trim(), weChat.getText().trim(), note.getText().trim());
        customerRequest.updateCustomer(customer);
        customerFrame.showData(customerRequest.getList());
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
                    cid.setText("");
                }
                cname.setText("");
                tel.setText("");
                weChat.setText("");
                note.setText("");
            }
        });
    }

    // 用于验证数据是否为空
    private boolean validation() {
        boolean flag = true;
        String cid = this.cid.getText().trim();
        String name = this.cname.getText().trim();
        String tel = this.tel.getText().trim();
        String weChat = this.weChat.getText().trim();
        String note = this.note.getText().trim();
        if (StringUtils.isBlank(cid) || StringUtils.isBlank(name) || StringUtils.isBlank(tel) || StringUtils.isBlank(weChat) || StringUtils.isBlank(note)) {
            flag = false;
            new MsgFrame("属性不能为空！");
            return flag;
        }
        if (!cid.matches("^[0-9]*$")) {
            flag = false;
            new MsgFrame("编号必须为数字！");
            return flag;
        }
        if (tel.length() > 11) {
            flag = false;
            new MsgFrame("电话长度不能大于11！");
            return flag;
        }
        return flag;
    }
}
