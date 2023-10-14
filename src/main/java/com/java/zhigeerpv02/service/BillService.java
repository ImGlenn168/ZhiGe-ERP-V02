package com.java.zhigeerpv02.service;

import com.java.zhigeerpv02.dao.bill.BillDao;
import com.java.zhigeerpv02.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillDao billDao;

    public List<Bill> findAllBills() {
        return billDao.findAllBills();
    }
}
