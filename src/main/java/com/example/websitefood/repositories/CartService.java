package com.example.websitefood.repositories;

import com.example.websitefood.entities.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();

    void save(Cart cart);
}
