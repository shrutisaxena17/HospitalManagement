package com.example.HospitalManagement.controller;
import com.example.HospitalManagement.DTO.PatientDTO;
import com.example.HospitalManagement.entity.Patient;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
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
 */
import com.example.HospitalManagement.DTO.PatientDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@PreAuthorize("hasAuthority('admin')")// Updated to an API-based route
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Add or save a new patient
    @PostMapping("/save")
    public ResponseEntity<String> savePatient(@RequestBody PatientDTO patientDTO) {
        patientService.savePatient(patientDTO);
        return new ResponseEntity<>("Patient saved successfully!", HttpStatus.CREATED);
    }

    // Update a patient
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable int id, @RequestBody PatientDTO patientDTO) {
        PatientDTO existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            patientDTO.setId(id);  // Ensure the ID matches
            patientService.savePatient(patientDTO);
            return new ResponseEntity<>("Patient updated successfully!", HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Patient not found for id " + id);
        }
    }

    // Get a patient by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('doctor') or hasAuthority('admin')")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id) {
        PatientDTO patient = patientService.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable int id) {
        patientService.deletePatientById(id);
        return new ResponseEntity<>("Patient deleted successfully!", HttpStatus.OK);
    }
}

