package com.example.HospitalManagement.service;

import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.entity.Address;
import com.example.HospitalManagement.entity.Doctor;
import com.example.HospitalManagement.entity.Phone;
import com.example.HospitalManagement.repo.DoctorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctorList=doctorRepo.findAll();
        List<DoctorDTO> doctorDTOList= doctorList.stream()
                .map(doctor -> modelMapper.map(doctor,DoctorDTO.class))
                .collect(Collectors.toList());
        return doctorDTOList;
    }

    public void saveDoctor(DoctorDTO doctorDTO){
        Doctor doctor=modelMapper.map(doctorDTO,Doctor.class);
        if (doctor.getPhones()!= null) {
            for (Phone phone : doctor.getPhones()) {
                phone.setDoctor(doctor);
            }
        }
        if (doctor.getAddresses()!= null) {
            for (Address address : doctor.getAddresses()) {
                address.setDoctor(doctor);
            }
        }
        doctorRepo.save(doctor);
    }

    public DoctorDTO getDoctorById(int id){
        Optional<Doctor> doctor=doctorRepo.findById(id);
        DoctorDTO doctorDTO = modelMapper.map(doctor,DoctorDTO.class);
        return doctorDTO;
    }

    public void deleteDoctorById(int id){
        doctorRepo.deleteById(id);
    }
}
