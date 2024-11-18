package com.example.websitefood.Controller;

import com.example.websitefood.entities.CartItem;
import com.example.websitefood.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public String showCartItems(Model model) {
        model.addAttribute("cartItems", cartItemService.getAllCartItems());
        return "CartItem/cartitem_list";
    }

    @GetMapping("/add")
    public String addCartItemForm(Model model) {
        model.addAttribute("cartItem", new CartItem());
        return "CartItem/cartitem_form";
    }

    @PostMapping("/save")
    public String saveCartItem(@ModelAttribute CartItem cartItem) {
        cartItemService.saveCartItem(cartItem);
        return "redirect:/cart-items";
    }

    @GetMapping("/{id}")
    public String viewCartItemDetail(@PathVariable Long id, Model model) {
        model.addAttribute("cartItem", cartItemService.getCartItemById(id));
        return "CartItem/cartitem_detail";
    }
}
