package com.java.zhigeerpv02.swing.bill;

import com.alibaba.excel.util.StringUtils;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.swing.request.BillRequest;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import com.java.zhigeerpv02.swing.util.OptionFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class BillFrame extends JFrame {

    private BillRequest billRequest = BillRequest.getBillRequest();

    private BillFrame billFrame = this;

    private List<Bill> billList;

    private OptionFrame optionFrame;
    JTable jTable;
    JScrollPane jScrollPane;
    JButton add, del, update, query, orderTimeQuery, export, goBackOption;
    JTextField check;
    JPanel disPlayArea;

    private String totalPrice;

    JLabel totalPriceLabel = new JLabel();

    /**
     * 账单列表展示
     */
    public BillFrame() {
        // 载入数据
        this.setBounds(380, 130, 1200, 800);
        this.setTitle("账单管理");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addController();
        this.showData(billRequest.getList());
        this.showTotalPrice(queryTotalPrice());
        this.setVisible(true);
    }

    public void addController() {
        jTable = new JTable();
        jTable.setShowGrid(true);
        jTable.setGridColor(Color.lightGray);

        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(2, 2, 1185, 600);

        goBackOption = new JButton("返回");
        goBackOption.setBounds(50, 630, 80, 25);

        orderTimeQuery = new JButton("下单区间查询");
        orderTimeQuery.setBounds(180, 630, 140, 25);

        check = new JTextField();
        check.setBounds(50, 680, 100, 25);

        query = new JButton("查询");
        query.setBounds(180, 680, 80, 25);

        export = new JButton("导出");
        export.setBounds(310, 680, 80, 25);

        add = new JButton("添加");
        add.setBounds(50, 720, 80, 25);

        update = new JButton("修改");
        update.setBounds(180, 720, 80, 25);

        del = new JButton("删除");
        del.setBounds(310, 720, 80, 25);

        disPlayArea = new JPanel();
        disPlayArea.setBounds(450, 650, 700, 50);
        disPlayArea.setBackground(Color.GREEN);
        Border blackline = BorderFactory.createLineBorder(Color.RED);
        disPlayArea.setBorder(blackline);


        addListener();
        add(disPlayArea);
        add(goBackOption);
        add(orderTimeQuery);
        add(query);
        add(check);
        add(export);
        add(add);
        add(update);
        add(del);
        add(jScrollPane);
    }

    // 为控件添加事件监听
    public void addListener() {
        backToOptions();
        orderTimeCheck();
        addBills();
        delBills();
        queryBills();
        updateBills();
        exportBills();
        queryTotalPrice();
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

    private void orderTimeCheck() {
        orderTimeQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new BillTimeQueryFrame(billFrame, BillAddAndUpdateFrame.CNAMES);
            }
        });
    }

    // 不同的集合展示不同的数据
    public void showData(List<Bill> billList) {

        // 居中显示数据
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 设置表格数据模型
        jTable.setModel(BillTableData.getModel(billList));

        // 设置居中显示渲染器
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 表格数据变化时，修改数据
        jTable.getModel().addTableModelListener(e -> {
            billRequest.updateBill(getCurrentBill());
        });
    }

    // 展示总价
    public void showTotalPrice(String tp) {
        totalPriceLabel.setText(tp);
        totalPriceLabel.setHorizontalAlignment(0);
        totalPriceLabel.setFont(new Font("楷书", 20, 30));
        disPlayArea.add(totalPriceLabel);
    }


    public void backToOptions() {
        goBackOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                billFrame.dispose();
                optionFrame = OptionFrame.getOptionFrame();
                optionFrame.setVisible(true);
            }
        });
    }

    public void addBills() {
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new BillAddAndUpdateFrame(billFrame, 1);
            }
        });
    }

    public void delBills() {
        del.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] rows = jTable.getSelectedRows();
                List<Integer> ids = new ArrayList<>();
                for (int i : rows) {
                    int id = Integer.parseInt((String) jTable.getValueAt(i, 0));
                    ids.add(id);
                }
                billRequest.removeBills(ids);
                showData(billRequest.getList());
            }
        });
    }

    public void updateBills() {
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getCurrentBill() != null) {
                    new BillAddAndUpdateFrame(billFrame, 2, getCurrentBill());
                }
            }
        });
    }

    /**
     * 查询金额
     *
     * @return
     */
    public String queryTotalPrice() {
        query.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (StringUtils.isBlank(check.getText().trim())) {
                    totalPrice = billRequest.getTotalPrice();
                    showTotalPrice("所有的账单金额：" + totalPrice);
                } else {
                    if (StringUtils.isNumeric(check.getText().trim())) {
                        totalPrice = billRequest.getTotalPriceByYear(check.getText().trim());
                        showTotalPrice(check.getText().trim() + "年的账单金额：" + totalPrice);
                    } else {
                        totalPrice = billRequest.getTotalPriceByName(check.getText().trim());
                        showTotalPrice(check.getText().trim() + "的账单金额：" + totalPrice);
                    }
                }
            }
        });
        return totalPrice;
    }

    /**
     * 查询按钮
     */
    public void queryBills() {
        query.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (StringUtils.isBlank(check.getText().trim())) {
                    billList = billRequest.getList();
                    showData(billList);
                } else {
                    if (StringUtils.isNumeric(check.getText().trim())) {
                        System.out.println(check.getText().trim() + " year");
                        billList = billRequest.getListByYear(check.getText().trim());
                        showData(billList);
                    } else {
                        System.out.println(check.getText().trim() + " name");
                        billList = billRequest.getListByName(check.getText().trim());
                        showData(billList);
                    }
                }
            }
        });
    }


    /**
     * 导出
     */
    public void exportBills() {
        export.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                billRequest.exportData();
            }
        });
    }

    // 获取当前选中的数据对象
    public Bill getCurrentBill() {
        int row = jTable.getSelectedRow();
        if (row == -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                new MsgFrame("请选择！");
                return null;
            }
        }
        return new Bill((String) jTable.getValueAt(row, 0), (String) jTable.getValueAt(row, 1),
                (String) jTable.getValueAt(row, 2), (String) jTable.getValueAt(row, 3),
                (String) jTable.getValueAt(row, 4), (String) jTable.getValueAt(row, 5),
                (String) jTable.getValueAt(row, 6), (String) jTable.getValueAt(row, 7),
                (String) jTable.getValueAt(row, 8));
    }

}
