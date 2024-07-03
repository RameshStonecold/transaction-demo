package com.example.transaction.controller;

import com.example.transaction.model.Address;
import com.example.transaction.model.Employee;
import com.example.transaction.model.EmployeeAddressMap;
import com.example.transaction.service.IAddressService;
import com.example.transaction.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final IEmployeeService service;
    private final IAddressService addressService;


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
       var result = service.getById(id);
        if(result==null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }



    @PostMapping ("/createEmployee")
    public ResponseEntity<String> createEmployee( @RequestBody EmployeeAddressMap employee){
         var result  = service.createEmployee(employee);
        if(result==null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }


    @GetMapping("/getAddressById/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id){
        var result = addressService.getById(id);
        if(result==null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

}
