package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    List<AddressDTO> getAll(){
        return addressService.getAllAddress();
    }


    @DeleteMapping("/{id}")
    void deleteUsingId(@PathVariable int id){
        addressService.deleteAddressById(id);
    }

    @GetMapping("/{id}")
    AddressDTO getById(@PathVariable int id){
        return addressService.getAddressById(id);
    }
}
