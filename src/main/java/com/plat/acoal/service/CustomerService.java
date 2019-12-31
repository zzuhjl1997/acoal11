package com.plat.acoal.service;


import com.plat.acoal.entity.Customer;

import java.util.List;

public interface CustomerService {
  List<Customer> selectAllCustomers(Customer customer);
}
