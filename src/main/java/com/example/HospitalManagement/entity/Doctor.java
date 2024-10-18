package com.example.HospitalManagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private int age;

    private String gender;

    private String specialization;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL)
    private List<Treatment> treatments;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL)
    private List<Phone> phones;

    @JsonIgnore
    @OneToMany(mappedBy ="doctor", cascade = CascadeType.ALL)
    private List<Address> addresses;

    public Doctor() {
    }

    public Doctor(int id, String firstName, String lastName, int age, String gender, String specialization, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
