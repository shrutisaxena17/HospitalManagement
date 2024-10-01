package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.DTO.DoctorDTO;
import com.example.HospitalManagement.DTO.TreatmentDTO;
import com.example.HospitalManagement.exception.IdNotFoundException;
import com.example.HospitalManagement.service.DoctorService;
import com.example.HospitalManagement.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
