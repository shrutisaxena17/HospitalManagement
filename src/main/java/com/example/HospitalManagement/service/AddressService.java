package com.example.HospitalManagement.service;

import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.entity.Address;
import com.example.HospitalManagement.repo.AddressRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<AddressDTO> getAllAddress(){
        List<Address> addressList= addressRepo.findAll();
        List<AddressDTO> addressDTOList=addressList.stream()
                .map(address -> modelMapper.map(address,AddressDTO.class))
                .collect(Collectors.toList());
        return addressDTOList;
    }

    public AddressDTO getAddressById(int id){
        Optional<Address> address=addressRepo.findById(id);
        AddressDTO addressDTO = modelMapper.map(address,AddressDTO.class);
        return addressDTO;
    }

    public void deleteAddressById(int id){
        addressRepo.deleteById(id);
    }
}
