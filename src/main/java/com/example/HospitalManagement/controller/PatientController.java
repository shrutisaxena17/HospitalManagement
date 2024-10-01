package com.example.HospitalManagement.controller;
import com.example.HospitalManagement.DTO.PatientDTO;
import com.example.HospitalManagement.entity.Patient;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public String getAll(Model model){
       List<PatientDTO> patient = patientService.getAllPatients();
       model.addAttribute("patient",patient);
       return "patient-list";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        Patient patient= new Patient();
        model.addAttribute("patient",patient);
        return "patient-add";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute PatientDTO patientDTO){
        patientService.savePatient(patientDTO);
        return "redirect:/patient";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId")int id, Model model){
        PatientDTO patient=patientService.getPatientById(id);
        model.addAttribute("patient",patient);
        return "patient-add";
    }

    @GetMapping("/delete")
    public String deleteUsingId(@RequestParam int id){
        patientService.deletePatientById(id);
        return "redirect:/patient";
    }


    @GetMapping("/{id}")
    PatientDTO getById(@PathVariable int id){
        return patientService.getPatientById(id);
    }
    @PostMapping("/search")
    public String showSearchForm(@RequestParam("patientId")int id, Model model) {
        PatientDTO patient=patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "patient-details";
        } else {
          throw new IdNotFoundException("Patient doesn't exist for id"+id);
        }
    }
}
