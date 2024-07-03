package com.example.transaction.service;

import com.example.transaction.model.Address;
import com.example.transaction.repository.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements IAddressService {
    private final AddressRepo addressRepo;


    @Override
    public void createAddress(Address address) {
        addressRepo.save(address);
    }

    @Override
    public Address getById(Long id) {
       var res = addressRepo.getReferenceById(id);
        return res;
    }
}
