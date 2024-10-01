package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
