package com.example.HospitalManagement.controller;
import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
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
*/


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/doctor")
@PreAuthorize("hasAuthority('admin')")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // GET all doctors
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Add a new doctor
    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>("Doctor added successfully!", HttpStatus.CREATED);
    }

    // Delete a doctor by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>("Doctor deleted successfully!", HttpStatus.OK);
    }

    // Update doctor information by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable int id, @RequestBody DoctorDTO doctorDTO) {
        doctorDTO.setId(id);
        doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>("Doctor updated successfully!", HttpStatus.OK);
    }

    // Get a doctor by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('receptionist') or hasAuthority('admin')")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable int id) {
        DoctorDTO doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Doctor doesn't exist with id " + id);
        }
    }
}

