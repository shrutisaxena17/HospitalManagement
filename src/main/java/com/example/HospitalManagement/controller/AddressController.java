package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public String getAll(Model model){
        List<AddressDTO> address = addressService.getAllAddress();
        model.addAttribute("address",address);
        return "address-list";
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
*/
import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/api/address")  // Changed to API-based route
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddress();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // Delete address by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable int id) {
        addressService.deleteAddressById(id);
        return new ResponseEntity<>("Address deleted successfully!", HttpStatus.OK);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable int id) {
        AddressDTO address = addressService.getAddressById(id);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}

