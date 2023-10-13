package com.java.zhigeerpv02.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.java.zhigeerpv02.dao.customer.CustomerDao;
import com.java.zhigeerpv02.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 新增客户信息
     *
     * @param customer
     */
    public int addCustomer(Customer customer) {
        System.out.println(customer);
        return customerDao.addCustomer(customer);

    }

    /**
     * 修改客户信息
     *
     * @param customer
     */
    public int updateCustomer(Customer customer) {
        List<Customer> allCustomer = customerDao.findAllCustomers();
        boolean flag = false;
        int i = 0;
        for (Customer customer1 : allCustomer) {
            if (customer1.getCid().equals(customer.getCid())) {
                flag = true;
            }
        }
        if (flag) {
            i = customerDao.updateCustomer(customer);
        }
        return i;
    }

    /**
     * @param ids
     * @return
     */
    public int removeCustomers(List<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            int i = customerDao.removeCustomer(id);
            count += i;
        }
        return count;
    }

    /**
     * 删除单个客户
     *
     * @param id
     * @return
     */
    public int removeCustomer(int id) {
        return customerDao.removeCustomer(id);
    }

    /**
     * 查询客户信息
     *
     * @param name
     * @return
     */
    public List<Customer> queryCustomers(String name) {
        if (!StringUtils.isBlank(name)) {
            return customerDao.findCustomerByName(name);
        }
        return customerDao.findAllCustomers();
    }

    /**
     * 获取所有customer
     *
     * @return
     */
    public List<Customer> getList() {
        List<Customer> allCustomers = customerDao.findAllCustomers();
        if (allCustomers.size() > 1) {
            return customerDao.findAllCustomers();
        } else return null;
    }

    /**
     * 保存客户信息到本地
     */
    public void savaData() {
        System.out.println("系统退出！");
    }

    public void exportData() {
        //1、创建一个文件对象
        File excelFile = new File("D:/客户信息.xlsx");
        //2、判断文件是否存在，不存在则创建一个Excel文件
        if (!excelFile.exists()) {
            try {
                excelFile.createNewFile();//创建一个新的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3、指定需要那个class去写。然后写到第一个sheet，名字为模版，然后文件流会自动关闭
        EasyExcel.write(excelFile, Customer.class).sheet("订单模版").doWrite(queryToExcel());
    }

    public List<Customer> queryToExcel() {
        //业务代码,获取数据集
        List<Customer> customers = customerDao.findAllCustomers();
        List<Customer> excels = new ArrayList<>();
        //遍历数据集，导出Excel
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            Customer data = new Customer();
            data.setCid(customer.getCid());
            data.setCname(customer.getCname());
            data.setPhoneNumber(customer.getPhoneNumber());
            data.setWeChat(customer.getWeChat());
            data.setNote(customer.getNote());
            excels.add(data);
        }
        return excels;
    }
}
