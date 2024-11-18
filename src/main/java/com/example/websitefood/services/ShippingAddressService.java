package com.example.websitefood.services;

import com.example.websitefood.entities.ShippingAddress;
import com.example.websitefood.repositories.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressRepository.findAll();
    }

    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAddressRepository.findById(id).orElse(null);
    }

    public void saveShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressRepository.save(shippingAddress);
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressRepository.deleteById(id);
    }
}
