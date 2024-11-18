package com.example.websitefood.Controller;

import com.example.websitefood.entities.Cart;
import com.example.websitefood.repositories.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    @RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Phương thức GET cho danh sách giỏ hàng
    @GetMapping
    public String getCarts(Model model) {
        model.addAttribute("carts", cartService.getAllCarts());
        return "cart/cart_list";
    }

    // Phương thức GET để hiển thị biểu mẫu thêm giỏ hàng
    @GetMapping("/add")
    public String addCart(Model model) {
        model.addAttribute("cart", new Cart());
        return "cart/cart_form";
    }

    // Phương thức POST để lưu giỏ hàng
    @PostMapping("/save")
    public String saveCart(@ModelAttribute Cart cart) {
        cartService.save(cart);  // Call to the service to save the cart
        return "redirect:/carts"; // After saving, redirect to the cart list
    }
}
