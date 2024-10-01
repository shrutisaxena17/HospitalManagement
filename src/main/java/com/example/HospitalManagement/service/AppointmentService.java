package com.example.HospitalManagement.service;
import com.example.HospitalManagement.DTO.AppointmentDTO;
import com.example.HospitalManagement.entity.Appointment;
import com.example.HospitalManagement.repo.AppointmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<AppointmentDTO> getAllAppointment(){
        List<Appointment> appoitmentList=appointmentRepo.findAll();
        List<AppointmentDTO> appointmentDTOList= appoitmentList.stream()
                .map(appointment -> modelMapper.map(appointment,AppointmentDTO.class))
                .collect(Collectors.toList());
        return appointmentDTOList;
    }

    public void saveAppointment(AppointmentDTO appointmentDTO){
        Appointment appointment=modelMapper.map(appointmentDTO,Appointment.class);
        if (appointment.getDoctor()!= null) {
            appointment.setDoctor(appointmentDTO.getDoctor());
        }
        if (appointment.getPatient()!= null) {
            appointment.setPatient(appointmentDTO.getPatient());
        }
        appointmentRepo.save(appointment);
    }

    public AppointmentDTO getAppointmentById(int id){
        Optional<Appointment> appointment=appointmentRepo.findById(id);
        AppointmentDTO appointmentDTO = modelMapper.map(appointment,AppointmentDTO.class);
        return appointmentDTO;
    }

    public void deleteAppointmentById(int id){
        appointmentRepo.deleteById(id);
    }
}
