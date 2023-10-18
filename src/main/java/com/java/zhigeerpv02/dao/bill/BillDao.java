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

    @Select("select cname from bill")
    List<String> findCNames();

    @UpdateProvider(value = BillSqlProvider.class, method = "addBill")
    int addBill(Bill bill);

    @UpdateProvider(value = BillSqlProvider.class, method = "updateBill")
    int updateBill(Bill bill);

    @Select("select * from bill where year = #{year}")
    List<Bill> getByYear(String year);

    @Select("select * from bill where cname = #{name}")
    List<Bill> getByName(String name);

    @Select("select totalPrice from bill")
    List<String> getTotalPrice();

    @Select("select totalPrice from bill where year = #{year}")
    List<String> getTotalPriceByYear(String year);

    @Select("select totalPrice from bill where cname = #{name}")
    List<String> getTotalPriceByName(String name);

    @UpdateProvider(value = BillSqlProvider.class, method = "removeBills")
    int removeBills(int id);

    @Select("select * from bill where orderTime BETWEEN #{startDay} and #{endDay}  and cname = #{name} ;")
    List<Bill> getBillsByOrderTimeAndName(String startDay, String endDay, String name);
}
