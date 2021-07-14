package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.CustomerDto;

public interface CustomerService {
    String addCustomerTransaction(CustomerDto customerDto);
}
