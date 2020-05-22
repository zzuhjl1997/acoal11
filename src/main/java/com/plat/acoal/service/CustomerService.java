package com.plat.acoal.service;


import com.plat.acoal.entity.Customer;
import com.plat.acoal.model.CustomerInfo;

import java.util.List;
import java.util.Map;

public interface CustomerService {
  List<Customer> selectAllCustomers(Customer customer);

  List<CustomerInfo> selectCustomerByCondition(Map<String, String> condition);
}
