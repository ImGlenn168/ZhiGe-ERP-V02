package com.java.zhigeerpv02.swing.util;

import com.java.zhigeerpv02.swing.customer.CustomerFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseUtil extends MouseAdapter {

    private JFrame frame = new JFrame();

    @Override
    public void mouseClicked(MouseEvent e) {
        //关闭
        frame.setDefaultCloseOperation(CustomerFrame.EXIT_ON_CLOSE);
        frame.dispose();
        System.exit(0);
    }

}
