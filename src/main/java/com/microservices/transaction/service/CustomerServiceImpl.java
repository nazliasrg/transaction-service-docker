package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.CustomerDto;
import com.microservices.transaction.model.dto.StatusMessageDto;
import com.microservices.transaction.model.entity.CustomerEntity;
import com.microservices.transaction.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String addCustomerTransaction(CustomerDto dto) {
        StatusMessageDto<CustomerEntity> result = new StatusMessageDto<>();

        if(dto.getUsername() != null){
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerId(dto.getCustomerId());
            customerEntity.setUsername(dto.getUsername());
            customerEntity.setPassword(dto.getPassword());
            customerEntity.setName(dto.getName());
            customerEntity.setEmail(dto.getEmail());
            customerEntity.setAddress(dto.getAddress());
            customerEntity.setPhoneNumber(dto.getPhoneNumber());
            customerEntity.setStatusAccount(dto.getStatusAccount());
            customerEntity.setSaldo(dto.getSaldo());

            customerRepository.save(customerEntity);

            result.setMessage("Customer " + customerEntity.getUsername() + " has been added successfully!");
            return result.getMessage();
        }
        else{
            result.setMessage("Username is empty!");
            return result.getMessage();
        }
    }
}
