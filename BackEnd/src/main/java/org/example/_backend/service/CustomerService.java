package org.example._backend.service;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    public List<Object[]> findAllCustomerDetails();

    int getCustomerCount();
}
