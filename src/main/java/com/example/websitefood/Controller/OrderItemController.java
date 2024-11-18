package com.example.websitefood.Controller;

import com.example.websitefood.entities.OrderItem;
import com.example.websitefood.services.OrderItemService;
import com.example.websitefood.services.OrderService;
import com.example.websitefood.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderService orderService, ProductService productService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String listOrderItems(Model model) {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        model.addAttribute("orderItems", orderItems);
        return "orderitem/orderitem_list";
    }

    @GetMapping("/new")
    public String createOrderItemForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        model.addAttribute("orders", orderService.getAllOrder());
        model.addAttribute("products", productService.getAllProducts());
        return "orderitem/orderitem_form";
    }

    @PostMapping("/save")
    public String saveOrderItem(@ModelAttribute OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order-items";
    }

    @GetMapping("/{id}/edit")
    public String editOrderItemForm(@PathVariable Long id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        if (orderItem != null) {
            model.addAttribute("orderItem", orderItem);
            model.addAttribute("orders", orderService.getAllOrder());
            model.addAttribute("products", productService.getAllProducts());
            return "orderitem/orderitem_form";
        } else {
            return "redirect:/order-items";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/order-items";
    }
}
