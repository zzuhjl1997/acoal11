package com.plat.acoal.service.impl;
import com.plat.acoal.dao.CustomerMapper;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.model.CustomerInfo;
import com.plat.acoal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
    public CustomerMapper customerMapper;
    //以注解的形式生成一个日志Service,
    @Override
    public List<Customer> selectAllCustomers(Customer customer) {
        return customerMapper.seletAllCustomers(customer);
    }

    @Override
    public List<CustomerInfo> selectCustomerByCondition(Map<String, String> condition) {
        return customerMapper.selectCustomesByCondition(condition);
    }
}
