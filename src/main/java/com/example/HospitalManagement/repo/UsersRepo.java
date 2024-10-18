package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepo extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}
