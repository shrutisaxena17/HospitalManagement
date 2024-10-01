package com.example.HospitalManagement.controller;
import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.entity.Doctor;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
   public String  getAll(Model model){
        List<DoctorDTO> doctor=doctorService.getAllDoctors();
        model.addAttribute("doctor",doctor);
        return "doctor-list";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor",doctor);
        return "doctor-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("doctor") DoctorDTO doctorDTO){
    doctorService.saveDoctor(doctorDTO);
    return "redirect:/doctor";
    }

    @GetMapping("/delete")
    String deleteUsingId(@PathVariable int id){
        doctorService.deleteDoctorById(id);
        return "redirect:/doctor";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("doctorId")int id, Model model){
        DoctorDTO doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor",doctor);
        return "doctor-add";
    }


    @PostMapping("/search")
    public String showSearchForm(@RequestParam("doctorId")int id, Model model) {
    DoctorDTO doctor= doctorService.getDoctorById(id);
        if (doctor != null) {
            model.addAttribute("doctor", doctor);
            return "doctor-details";
        } else {
            throw new IdNotFoundException("Doctor doesn't exist with id "+id);
        }
    }
}
