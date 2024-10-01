package com.example.HospitalManagement.DTO;

import com.example.HospitalManagement.entity.Address;
import com.example.HospitalManagement.entity.Appointment;
import com.example.HospitalManagement.entity.Phone;
import com.example.HospitalManagement.entity.Treatment;

import java.util.List;

public class PatientDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private List<Treatment> treatments;
    private List<Appointment> appointments;
    private List<Phone> phones;
    private List<Address> addresses;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
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
