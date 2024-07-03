package com.example.transaction.service;


import com.example.transaction.model.Employee;
import com.example.transaction.model.EmployeeAddressMap;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {

     String createEmployee(EmployeeAddressMap employee);

    Employee getById(Long id);
}
