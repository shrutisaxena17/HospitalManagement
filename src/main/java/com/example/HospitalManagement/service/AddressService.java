package com.example.HospitalManagement.service;

import com.example.HospitalManagement.DTO.AddressDTO;
import com.example.HospitalManagement.entity.Address;
import com.example.HospitalManagement.repo.AddressRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<AddressDTO> getAllAddress() {
        List<Address> addressList = addressRepo.findAll();
        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    public AddressDTO getAddressById(int id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            return modelMapper.map(optionalAddress.get(), AddressDTO.class);
        } else {
            // Handle the case where the address is not found
            throw new RuntimeException("Address not found for ID: " + id);
            // Or return null, or create a custom exception to handle this
        }
    }

    public void deleteAddressById(int id) {
        if (addressRepo.existsById(id)) {
            addressRepo.deleteById(id);
        } else {
            throw new RuntimeException("Address not found for ID: " + id);
        }
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressRepo.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    public AddressDTO updateAddress(int id, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address addressToUpdate = optionalAddress.get();
            // Update fields as necessary, using addressDTO
            addressToUpdate.setStreet(addressDTO.getStreet());
            addressToUpdate.setCity(addressDTO.getCity());
            addressToUpdate.setState(addressDTO.getState());
            addressToUpdate.setCountry(addressDTO.getCountry());
            addressToUpdate.setPincode(addressDTO.getPincode());
            // Add more fields if necessary

            Address updatedAddress = addressRepo.save(addressToUpdate);
            return modelMapper.map(updatedAddress, AddressDTO.class);
        } else {
            throw new RuntimeException("Address not found for ID: " + id);
        }
    }
}
