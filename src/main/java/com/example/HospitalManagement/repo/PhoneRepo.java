package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepo extends JpaRepository<Phone,Integer> {
}
