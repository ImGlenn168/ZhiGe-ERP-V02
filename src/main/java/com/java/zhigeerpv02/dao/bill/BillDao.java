package com.java.zhigeerpv02.dao.bill;

import com.java.zhigeerpv02.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillDao {

    @Select("select * from bill")
    List<Bill> findAllBills();


}
