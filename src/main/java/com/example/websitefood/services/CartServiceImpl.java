package com.example.websitefood.services;

import com.example.websitefood.entities.Cart;
import com.example.websitefood.repositories.CartRepository;
import com.example.websitefood.repositories.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void save(Cart cart) {

    }
}
