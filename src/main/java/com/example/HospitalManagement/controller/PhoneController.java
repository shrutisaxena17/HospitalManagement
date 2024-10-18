package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.DTO.PhoneDTO;
import com.example.HospitalManagement.service.AddressService;
import com.example.HospitalManagement.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/api/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @GetMapping
    List<PhoneDTO> getAll(){
        return phoneService.getAllPhone();
    }


    @DeleteMapping("/{id}")
    void deleteUsingId(@PathVariable int id){
        phoneService.deletePhoneById(id);
    }

    @GetMapping("/{id}")
    PhoneDTO getById(@PathVariable int id){
        return phoneService.getPhoneById(id);
    }
}
