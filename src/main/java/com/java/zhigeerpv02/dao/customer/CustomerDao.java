package com.java.zhigeerpv02.dao.customer;

import com.java.zhigeerpv02.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface CustomerDao {

    @Select("select * from customer")
    List<Customer> findAllCustomers();

    // 根据姓名模糊查询数据
    @SelectProvider(value = CustomerSqlProvider.class, method = "findCustomerByName")
    List<Customer> findCustomerByName(String cname);

    @UpdateProvider(value = CustomerSqlProvider.class, method = "addCustomer")
    int addCustomer(Customer customer);

    // 删除选中数据
    @UpdateProvider(value = CustomerSqlProvider.class, method = "removeCustomer")
    int removeCustomer(int id);

    // 修改数据
    @UpdateProvider(value = CustomerSqlProvider.class, method = "updateCustomer")
    int updateCustomer(Customer customer);

    // TODO 退出时保存数据
    void savaData();

    // TODO 删除多条
    int removeCustomers(List<Integer> ids);
}
