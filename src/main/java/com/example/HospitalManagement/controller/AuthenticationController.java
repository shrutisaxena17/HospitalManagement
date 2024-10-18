package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.security.AuthenticationRequest;
import com.example.HospitalManagement.security.AuthenticationResponse;
import com.example.HospitalManagement.entity.Users;
import com.example.HospitalManagement.repo.UsersRepo;
import com.example.HospitalManagement.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JwtService jwtService;

    // Authentication endpoint
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        // Perform authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Load the authenticated member details
        Optional<Users> usersOptional = Optional.ofNullable(usersRepo.findByUsername(request.getUsername()));
        Users users = usersOptional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + request.getUsername()));

        String role= users.getRoles();
        // Generate JWT token using the username
        String jwt = jwtService.generateToken(users.getUsername(),role);

        return new AuthenticationResponse(jwt);
    }
}
