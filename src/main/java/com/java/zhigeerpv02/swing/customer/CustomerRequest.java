package com.java.zhigeerpv02.swing.customer;

import com.alibaba.fastjson.JSON;
import com.java.zhigeerpv02.entity.Customer;
import com.java.zhigeerpv02.swing.util.MsgFrame;
import com.java.zhigeerpv02.utils.RestTemplateUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CustomerRequest {

    private static CustomerRequest customerRequest;

    public static CustomerRequest getCustomerRequest() {
        if (customerRequest == null) {
            return new CustomerRequest();
        }
        return customerRequest;
    }

    public List<Customer> getList() {
        List customerList = RestTemplateUtil.getRestTemplate().getForObject(RestTemplateUtil.URL + "/customer/findAll", List.class);
        String strList = JSON.toJSONString(customerList, Customer.class.getModifiers());
        List<Customer> customers = JSON.parseArray(strList, Customer.class);
        return customers;
    }

    public List<Customer> getListByName(String nameCheck) {
        List customerList = RestTemplateUtil.getRestTemplate()
                .getForObject(RestTemplateUtil.URL + "/customer/findByName/" + nameCheck,
                        List.class);
        String strList = JSON.toJSONString(customerList, Customer.class.getModifiers());
        List<Customer> customers = JSON.parseArray(strList, Customer.class);
        return customers;
    }

    public void exportData() {
        RestTemplateUtil.getRestTemplate().getForEntity(RestTemplateUtil.URL + "/customer/export", Void.class);
        new MsgFrame("文件已存入D:/客户信息.xlsx");
    }

    public void removeCustomers(List<Integer> ids) {
        ResponseEntity<Integer> result = RestTemplateUtil.getRestTemplate().postForEntity(RestTemplateUtil.URL + "/customer/remove", ids, Integer.class);
        int count = result.getBody();
        if (count > 0) {
            new MsgFrame("成功删除了" + count + "条数据！");
        } else {
            new MsgFrame("删除失败");
        }
    }

    public void addCustomer(Customer customer) {
        ResponseEntity<Integer> result = RestTemplateUtil.getRestTemplate().postForEntity(RestTemplateUtil.URL + "/customer/add", customer, Integer.class);
        Integer i = result.getBody();

        if (i > 0) {
            new MsgFrame("客户新增成功！");
        } else {
            new MsgFrame("客户新增失败！");
        }
    }

    public void updateCustomer(Customer customer) {
        ResponseEntity<Integer> result = RestTemplateUtil.getRestTemplate().postForEntity(RestTemplateUtil.URL + "/customer/update", customer, Integer.class);
        Integer i = result.getBody();
        if (i > 0) {
            new MsgFrame("客户修改成功！");
        } else {
            new MsgFrame("客户修改失败！");
        }
    }

}
