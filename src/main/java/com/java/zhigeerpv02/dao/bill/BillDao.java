package com.java.zhigeerpv02.dao.bill;

import com.java.zhigeerpv02.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface BillDao {

    @Select("select * from bill")
    List<Bill> findAllBills();

    @UpdateProvider(value = BillSqlProvider.class, method = "addBill")
    int addBill(Bill bill);

    @UpdateProvider(value = BillSqlProvider.class, method = "updateBill")
    int updateBill(Bill bill);
}
