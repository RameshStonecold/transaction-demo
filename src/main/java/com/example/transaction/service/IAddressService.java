package com.example.transaction.service;

import com.example.transaction.model.Address;
import com.example.transaction.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface IAddressService {

    void createAddress(Address address);
    Address getById(Long id);
}
