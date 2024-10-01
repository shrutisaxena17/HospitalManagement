package com.example.HospitalManagement.service;
import com.example.HospitalManagement.DTO.PhoneDTO;
import com.example.HospitalManagement.entity.Phone;
import com.example.HospitalManagement.repo.PhoneRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    @Autowired
    PhoneRepo phoneRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<PhoneDTO> getAllPhone(){
        List<Phone> phoneList= phoneRepo.findAll();
        List<PhoneDTO> phoneDTOList=phoneList.stream()
                .map(phone -> modelMapper.map(phone,PhoneDTO.class))
                .collect(Collectors.toList());
        return phoneDTOList;
    }

    public PhoneDTO getPhoneById(int id){
        Optional<Phone> phone=phoneRepo.findById(id);
        PhoneDTO phoneDTO = modelMapper.map(phone,PhoneDTO.class);
        return phoneDTO;
    }

    public void deletePhoneById(int id){
        phoneRepo.deleteById(id);
    }
}
