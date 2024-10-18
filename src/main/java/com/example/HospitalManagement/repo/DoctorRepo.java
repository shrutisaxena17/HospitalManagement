package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}
