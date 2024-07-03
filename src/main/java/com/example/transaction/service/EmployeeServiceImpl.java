package com.example.transaction.service;


import com.example.transaction.model.Employee;
import com.example.transaction.model.EmployeeAddressMap;
import com.example.transaction.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private  final IAddressService addressService;
   private final EmployeeRepo repo;



    @Transactional
    @Override
    public String createEmployee(EmployeeAddressMap emp)  {

        try {

            repo.save(emp.getEmployee());
            addressService.createAddress(emp.getAddress());
            return "Employee with address details saved !";

        }catch (Exception e){
             e.printStackTrace();
        }
         return null;
    }

    @Override
    public Employee getById(Long id) {
       Optional<Employee> optional = repo.findById(id);
        return optional.orElse(null);
    }


}
