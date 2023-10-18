package com.java.zhigeerpv02.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.java.zhigeerpv02.dao.bill.BillDao;
import com.java.zhigeerpv02.entity.Bill;
import com.java.zhigeerpv02.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    private String[] names = {"宏福", "赛锐", "中流", "能通", "明正", "修兵", "修明", "何玺", "陈杰", "阿刚", "夏传荣", "松子"};
    @Autowired
    private BillDao billDao;

    public List<Bill> findAllBills() {
        return billDao.findAllBills();
    }

    public Result addBill(Bill bill) {
        Result validator = validator(bill);
        if (validator != null) return validator;
        int totalPrice = Integer.parseInt(bill.getQuantity()) * Integer.parseInt(bill.getUnitPrice());
        bill.setTotalPrice(String.valueOf(totalPrice));
        bill.setYear(bill.getOrderTime().substring(0, 4));
        int i = 0;
        try {
            i = billDao.addBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    private static Result validator(Bill bill) {
        if (StringUtils.isBlank(bill.getCname())) {
            return Result.result("客户名不能为空！");
        }
        if (StringUtils.isBlank(bill.getOrderTime())) {
            return Result.result("下单时间不能为空！");
        }
        if (StringUtils.isBlank(bill.getQuantity())) {
            return Result.result("数量不能为空！");
        }
        if (StringUtils.isBlank(bill.getUnitPrice())) {
            return Result.result("单价不能为空！");
        }
        if (!StringUtils.isNumeric(bill.getQuantity()) || !StringUtils.isNumeric(bill.getQuantity())) {
            return Result.result("单价或数量必须为正整数！");
        }
        return null;
    }

    public Result updateBill(Bill bill) {
        Result validator = validator(bill);
        if (validator != null) return validator;
        int totalPrice = Integer.parseInt(bill.getQuantity()) * Integer.parseInt(bill.getUnitPrice());
        bill.setTotalPrice(String.valueOf(totalPrice));
        bill.setYear(bill.getOrderTime().substring(0, 4));
        int i = 0;
        try {
            i = billDao.updateBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    public Result getByYear(String year) {
        try {
            List<Bill> bills = billDao.getByYear(year);
            if (bills.size() > 0) {
                return Result.success(bills);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    public Result getByName(String name) {
        try {
            List<Bill> bills = billDao.getByName(name);
            if (bills.size() > 0) {
                return Result.success(bills);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    public Result getTotalPrice() {
        int price = 0;
        List<String> totalPrice = billDao.getTotalPrice();
        return Result.success(getTotalPrice(price, totalPrice));
    }

    public Result getTotalPriceByYear(String year) {
        int price = 0;
        List<String> totalPrice = billDao.getTotalPriceByYear(year);
        return Result.success(getTotalPrice(price, totalPrice));
    }

    private static int getTotalPrice(int price, List<String> totalPrice) {
        for (String tp : totalPrice) {
            int parsePrice = Integer.parseInt(tp);
            price += parsePrice;
        }
        return price;
    }

    public Result getTotalPriceByName(String name) {
        int price = 0;
        List<String> totalPrice = billDao.getTotalPriceByName(name);
        return Result.success(getTotalPrice(price, totalPrice));
    }

    public Result getBillsByOrderTimeAndName(String startDay, String endDay, String name) {
        try {
            List<Bill> priceList = billDao.getBillsByOrderTimeAndName(startDay, endDay, name);
            return Result.success(priceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    public Result removeBills(List<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            int i = 0;
            try {
                i = billDao.removeBills(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count += i;
        }
        return Result.success(count);
    }

    public void exportBills() {
        //1、创建一个文件对象
        File excelFile = new File("D:/账单信息.xlsx");
        //2、判断文件是否存在，不存在则创建一个Excel文件
        if (!excelFile.exists()) {
            try {
                excelFile.createNewFile();//创建一个新的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3、指定需要那个class去写。然后写到第一个sheet，名字为模版，然后文件流会自动关闭
        ExcelWriter excelWriter = EasyExcel.write(excelFile, Bill.class).build();

        // 4. 业务代码,获取数据集
        List<Bill> bills = billDao.findAllBills();
        WriteSheet writeSheet;

        List<Bill> dataExcels = new ArrayList<>();
        writeSheet = EasyExcel.writerSheet(0, "所有账单").build();
        excelWriter.write(queryToExcel(bills, dataExcels), writeSheet);
        for (int i = 0; i < names.length; i++) {
            List<Bill> cdataExcel = new ArrayList<>();
            for (Bill bill : bills) {
                if (names[i].equals(bill.getCname())) {
                    getBillData(bills, cdataExcel, i);
                }
            }
            writeSheet = EasyExcel.writerSheet(i + 1, names[i] + "账单").build();
            excelWriter.write(cdataExcel, writeSheet);
        }
        excelWriter.finish();
    }

    public List<Bill> queryToExcel(List<Bill> bills, List<Bill> dataExcels) {
        //业务代码,获取数据集
        for (int i = 0; i < bills.size(); i++) {
            getBillData(bills, dataExcels, i);
        }
        return dataExcels;
    }

    private void getBillData(List<Bill> bills, List<Bill> cdataExcel, int i) {
        Bill data = new Bill();
        Bill bill = bills.get(i);
        data.setBid(bill.getBid());
        data.setCname(bill.getCname());
        data.setOrderTime(bill.getOrderTime());
        data.setQuantity(bill.getQuantity());
        data.setUnitPrice(bill.getUnitPrice());
        data.setTotalPrice(bill.getTotalPrice());
        data.setCreateTime(bill.getCreateTime());
        data.setYear(bill.getYear());
        data.setNote(bill.getNote());
        cdataExcel.add(data);
    }
}
