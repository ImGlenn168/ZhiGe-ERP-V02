package com.java.zhigeerpv02.swing.customer;

import com.alibaba.excel.util.StringUtils;
import com.java.zhigeerpv02.entity.Customer;
import com.java.zhigeerpv02.swing.request.CustomerRequest;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import com.java.zhigeerpv02.swing.util.CustomerTableData;
import com.java.zhigeerpv02.swing.util.OptionFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomerFrame extends JFrame {

    private CustomerRequest customerRequest = CustomerRequest.getCustomerRequest();
    private CustomerFrame customerFrame = this;

    private OptionFrame optionFrame;
    JTable jTable;
    JScrollPane jScrollPane;
    JButton add, del, update, query, export, goBackOption;
    JTextField nameCheck;

    /**
     * 客户列表展示
     */
    public CustomerFrame() {
        // 载入数据
        this.setBounds(500, 250, 800, 500);
        this.setTitle("客户管理");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addCom();
        this.showData(customerRequest.getList());
        this.setVisible(true);
    }

    /**
     * 主要控件
     */
    public void addCom() {
        jTable = new JTable();
        jTable.setShowGrid(true);
        jTable.setGridColor(Color.lightGray);

        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(2, 2, 790, 300);

        goBackOption = new JButton("返回");
        goBackOption.setBounds(50, 320, 80, 25);

        add = new JButton("添加");
        add.setBounds(50, 410, 80, 25);

        update = new JButton("修改");
        update.setBounds(180, 410, 80, 25);

        del = new JButton("删除");
        del.setBounds(310, 410, 80, 25);

        query = new JButton("查询");
        query.setBounds(180, 370, 80, 25);

        export = new JButton("导出");
        export.setBounds(310, 370, 80, 25);

        nameCheck = new JTextField();
        nameCheck.setBounds(50, 370, 100, 25);

        addListener();
        add(goBackOption);
        add(add);
        add(update);
        add(del);
        add(query);
        add(export);
        add(nameCheck);
        add(jScrollPane);
    }

    // 不同的集合展示不同的数据
    public void showData(List<Customer> data) {
        // 居中显示数据
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 设置表格数据模型
        jTable.setModel(CustomerTableData.getModel(data));
        // 设置居中显示渲染器
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 表格数据变化时，修改数据
        jTable.getModel().addTableModelListener(e -> {
            customerRequest.updateCustomer(getCurrentCustomer());
        });
    }

    // 为控件添加事件监听
    public void addListener() {
        backToOptions();
        addCustomer();
        delCustomer();
        queryCustomer();
        updateCustomer();
        exportCustomer();
        // 关闭窗口时，保存数据到文件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO 使用数据库后无需存入文件
//                customerService.savaData();
//                dispose();
//                new OptionFrame();
            }
        });
    }

    public void backToOptions(){
        goBackOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerFrame.dispose();
                optionFrame = OptionFrame.getOptionFrame();
                optionFrame.setVisible(true);
            }
        });
    }

    public void addCustomer() {
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CustomerAddAndUpdateFrame(customerFrame, 1);
            }
        });
    }

    public void delCustomer() {
        del.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] rows = jTable.getSelectedRows();
                List<Integer> ids = new ArrayList<>();
                for (int i : rows) {
                    int id = Integer.parseInt((String) jTable.getValueAt(i, 0));
                    ids.add(id);
                }
                customerRequest.removeCustomers(ids);
                showData(customerRequest.getList());
            }
        });
    }

    public void updateCustomer() {
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getCurrentCustomer() != null) {
                    new CustomerAddAndUpdateFrame(customerFrame, 2, getCurrentCustomer());
                }
            }
        });
    }

    public void queryCustomer() {
        query.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (StringUtils.isBlank(nameCheck.getText().trim())) {
                    showData(customerRequest.getList());
                } else {
                    showData(customerRequest.getListByName(nameCheck.getText().trim()));
                }
            }
        });
    }


    /**
     * 导出
     */
    public void exportCustomer() {
        export.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerRequest.exportData();
            }
        });
    }

    // 获取当前选中的数据对象
    public Customer getCurrentCustomer() {
        int row = jTable.getSelectedRow();
        if (row == -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                new MsgFrame("请选择！");
                return null;
            }
        }
        return new Customer((String) jTable.getValueAt(row, 0), (String) jTable.getValueAt(row, 1),
                (String) jTable.getValueAt(row, 2), (String) jTable.getValueAt(row, 3), (String) jTable.getValueAt(row, 4));
    }


}
