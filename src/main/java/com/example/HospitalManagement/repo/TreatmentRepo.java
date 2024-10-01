package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepo extends JpaRepository<Treatment,Integer> {
}
