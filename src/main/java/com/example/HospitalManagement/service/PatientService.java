package com.example.HospitalManagement.service;
import com.example.HospitalManagement.DTO.PatientDTO;
import com.example.HospitalManagement.entity.Address;
import com.example.HospitalManagement.entity.Patient;
import com.example.HospitalManagement.entity.Phone;
import com.example.HospitalManagement.repo.PatientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<PatientDTO> getAllPatients(){
        List<Patient> patientList= patientRepo.findAll();
        List<PatientDTO> patientDTOList=patientList.stream()
                .map(patient -> modelMapper.map(patient,PatientDTO.class))
                .collect(Collectors.toList());
        return patientDTOList;
    }

    public void savePatient(PatientDTO patientDTO){
        Patient patient=modelMapper.map(patientDTO,Patient.class);
        if (patient.getPhones()!= null) {
            for (Phone phone : patient.getPhones()) {
                phone.setPatient(patient);
            }
        }
        if (patient.getAddresses()!= null) {
            for (Address address : patient.getAddresses()) {
                address.setPatient(patient);
            }
        }
        patientRepo.save(patient);
    }

    public PatientDTO getPatientById(int id){
        Optional<Patient> patient=patientRepo.findById(id);
        PatientDTO patientDTO = modelMapper.map(patient,PatientDTO.class);
        return patientDTO;
    }

    public void deletePatientById(int id){
        patientRepo.deleteById(id);
    }
}
