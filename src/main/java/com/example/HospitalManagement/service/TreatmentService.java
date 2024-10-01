package com.example.HospitalManagement.service;

import com.example.HospitalManagement.DTO.TreatmentDTO;
import com.example.HospitalManagement.entity.*;
import com.example.HospitalManagement.repo.TreatmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentService {
    @Autowired
    TreatmentRepo treatmentRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<TreatmentDTO> getAllTreatment(){
        List<Treatment> treatmentList=treatmentRepo.findAll();
        List<TreatmentDTO> treatmentDTOList= treatmentList.stream()
                .map(treatment -> modelMapper.map(treatment,TreatmentDTO.class))
                .collect(Collectors.toList());
        return treatmentDTOList;
    }

    public void saveTreatment(TreatmentDTO treatmentDTO){
        Treatment treatment=modelMapper.map(treatmentDTO, Treatment.class);
        if (treatment.getDoctor()!= null) {
                treatment.setDoctor(treatmentDTO.getDoctor());
        }
        if (treatment.getPatient()!= null) {
               treatment.setPatient(treatmentDTO.getPatient());
        }
        treatmentRepo.save(treatment);
    }

    public TreatmentDTO getTreatmentById(int id){
        Optional<Treatment> treatment=treatmentRepo.findById(id);
        TreatmentDTO treatmentDTO = modelMapper.map(treatment,TreatmentDTO.class);
        return treatmentDTO;
    }

    public void deleteTreatmentById(int id){
        treatmentRepo.deleteById(id);
    }
}

