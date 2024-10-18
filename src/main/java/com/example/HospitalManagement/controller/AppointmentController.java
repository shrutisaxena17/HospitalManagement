package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.AppointmentDTO;
import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping
    public String getAll(Model model){
    List<AppointmentDTO> appointment = appointmentService.getAllAppointment();
    model.addAttribute("appointment",appointment);
    return "appointment-list";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        AppointmentDTO appointment= new AppointmentDTO();
        model.addAttribute("appointment",appointment);
        return "appointment-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("appointment") AppointmentDTO appointmentDTO){
        appointmentService.saveAppointment(appointmentDTO);
        return "redirect:/appointment";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("appointmentId")int id, Model model){
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment",appointment);
        return "appointment-add";
    }

    @GetMapping("/delete")
    void deleteUsingId(@PathVariable int id){
        appointmentService.deleteAppointmentById(id);
    }
    @PostMapping("/search")
    public String showSearchForm(@RequestParam("appointmentId")int id, Model model) {
        AppointmentDTO appointment= appointmentService.getAppointmentById(id);
        if (appointment != null) {
            model.addAttribute("appointment", appointment);
            return "appointment-details";
        } else {
            throw new IdNotFoundException("Appointment doesn't exist for id"+id);
        }
    }
}
*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/appointment")
@PreAuthorize("hasAuthority('receptionist')")// Changed to API-based route
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // GET all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointment();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Add a new appointment
    @PostMapping("/add")
    public ResponseEntity<String> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.saveAppointment(appointmentDTO);
        return new ResponseEntity<>("Appointment added successfully!", HttpStatus.CREATED);
    }

    // Update an appointment by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable int id, @RequestBody AppointmentDTO appointmentDTO) {
        appointmentDTO.setId(id);
        appointmentService.saveAppointment(appointmentDTO);
        return new ResponseEntity<>("Appointment updated successfully!", HttpStatus.OK);
    }

    // Delete an appointment by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable int id) {
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>("Appointment deleted successfully!", HttpStatus.OK);
    }

    // Get an appointment by ID

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient') or hasAuthority('receptionist')")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable int id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Appointment doesn't exist with id " + id);
        }
    }
}
