package com.java.zhigeerpv02;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.java.zhigeerpv02.swing.login.LoginFrame;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;

@SpringBootApplication
@MapperScan("com.java.zhigeerpv02.dao") //扫描dao
@EnableScheduling //开启定时任务
public class ZhiGeErpV02Application {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatLightLaf.install();
                new LoginFrame().setVisible(true);
            }
        });
        SpringApplication.run(ZhiGeErpV02Application.class, args);

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(ZhiGeErpV02Application.class);
//        builder.headless(false).web(WebApplicationType.SERVLET).run(args);

    }

}
