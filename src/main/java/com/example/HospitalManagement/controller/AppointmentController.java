package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.AppointmentDTO;
import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
