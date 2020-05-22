package com.plat.acoal.dao;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.model.CustomerInfo;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Customer record);
    int insertSelective(Customer record);
    Customer selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Customer record);
    int updateByPrimaryKey(Customer record);
    List<Customer> seletAllCustomers(Customer customer);

    List<CustomerInfo> selectCustomesByCondition(Map<String, String> condition);
}