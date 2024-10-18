package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.DTO.TreatmentDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.DoctorService;
import com.example.HospitalManagement.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@Controller
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    TreatmentService treatmentService;

    @GetMapping
    public String getAll(Model model){
        List<TreatmentDTO>  treatment = treatmentService.getAllTreatment();
        model.addAttribute("treatment",treatment);
        return "treatment-list";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        TreatmentDTO treatment = new TreatmentDTO();
        model.addAttribute("treatment",treatment);
        return "treatment-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TreatmentDTO treatmentDTO){
        treatmentService.saveTreatment(treatmentDTO);
        return "redirect:/treatment";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("treatmentId")int id, Model model){
        TreatmentDTO treatment = treatmentService.getTreatmentById(id);
        model.addAttribute("treatment",treatment);
        return "treatment-add";
    }

    @GetMapping("/delete")
    public String deleteUsingId(@PathVariable int id){
        treatmentService.deleteTreatmentById(id);
        return "redirect:/treatment";
    }

    @PostMapping("/search")
    public String showSearchForm(@RequestParam("treatmentId")int id, Model model) {
        TreatmentDTO treatment = treatmentService.getTreatmentById(id);
        if (treatment!= null) {
            model.addAttribute("treatment",treatment);
            return "treatment-details";
        } else {
         throw new IdNotFoundException("Treatment doesn't exist for id "+id);
        }
    }

    @GetMapping("/{id}")
    TreatmentDTO getById(@PathVariable int id){
        return treatmentService.getTreatmentById(id);
    }
}

 */
import com.example.HospitalManagement.DTO.TreatmentDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatment")
@PreAuthorize("hasAuthority('doctor')")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    // Get all treatments
    @GetMapping
    public ResponseEntity<List<TreatmentDTO>> getAllTreatments() {
        List<TreatmentDTO> treatments = treatmentService.getAllTreatment();
        return new ResponseEntity<>(treatments, HttpStatus.OK);
    }

    // Add a new treatment
    @PostMapping
    public ResponseEntity<String> addTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        treatmentService.saveTreatment(treatmentDTO);
        return new ResponseEntity<>("Treatment saved successfully", HttpStatus.CREATED);
    }

    // Update an existing treatment
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTreatment(@PathVariable int id, @RequestBody TreatmentDTO treatmentDTO) {
        TreatmentDTO existingTreatment = treatmentService.getTreatmentById(id);
        if (existingTreatment != null) {
            treatmentDTO.setId(id); // Ensure the ID is updated
            treatmentService.saveTreatment(treatmentDTO);
            return new ResponseEntity<>("Treatment updated successfully", HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Treatment doesn't exist for id " + id);
        }
    }

    // Delete a treatment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTreatment(@PathVariable int id) {
        TreatmentDTO existingTreatment = treatmentService.getTreatmentById(id);
        if (existingTreatment != null) {
            treatmentService.deleteTreatmentById(id);
            return new ResponseEntity<>("Treatment deleted successfully", HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Treatment doesn't exist for id " + id);
        }
    }

    // Get a treatment by ID
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentDTO> getTreatmentById(@PathVariable int id) {
        TreatmentDTO treatment = treatmentService.getTreatmentById(id);
        if (treatment != null) {
            return new ResponseEntity<>(treatment, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Treatment doesn't exist for id " + id);
        }
    }
}
