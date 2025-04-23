package org.example._backend.service.impl;

import org.example._backend.dao.CustomerDAO;
import org.example._backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    @Override
    public List<Object[]> findAllCustomerDetails() {
      return   null;
    }

    @Override
    public int getCustomerCount() {
     return    customerDAO.countAllCustomers();
    }


}
