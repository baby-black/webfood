package com.example.websitefood.Controller;

import com.example.websitefood.entities.ShippingAddress;
import com.example.websitefood.services.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shipping_addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    // Hiển thị danh sách địa chỉ giao hàng
    @GetMapping
    public String getAllShippingAddresses(Model model) {
        List<ShippingAddress> shippingAddresses = shippingAddressService.getAllShippingAddresses();
        model.addAttribute("shippingAddresses", shippingAddresses);
        return "ShippingAddress/shippingaddress_list";
    }

    // Hiển thị form thêm địa chỉ giao hàng mới
    @GetMapping("/new")
    public String addShippingAddressForm(Model model) {
        ShippingAddress shippingAddress = new ShippingAddress();
        model.addAttribute("shippingAddress", shippingAddress);
        return "ShippingAddress/shippingaddress_form";
    }

    // Lưu địa chỉ giao hàng mới
    @PostMapping("/save")
    public String saveShippingAddress(@ModelAttribute ShippingAddress shippingAddress) {
        shippingAddressService.saveShippingAddress(shippingAddress);
        return "redirect:/shipping-addresses"; // Sau khi lưu, chuyển hướng về danh sách địa chỉ giao hàng
    }

    // Hiển thị form chỉnh sửa địa chỉ giao hàng
    @GetMapping("/{id}/edit")
    public String editShippingAddressForm(@PathVariable Long id, Model model) {
        ShippingAddress shippingAddress = shippingAddressService.getShippingAddressById(id);
        if (shippingAddress != null) {
            model.addAttribute("shippingAddress", shippingAddress);
            return "ShippingAddress/shippingaddress_form"; // Hiển thị form chỉnh sửa
        } else {
            return "redirect:/shipping-addresses"; // Nếu không tìm thấy địa chỉ, quay lại danh sách
        }
    }

    // Cập nhật địa chỉ giao hàng
    @PostMapping("/{id}")
    public String updateShippingAddress(@PathVariable Long id, @ModelAttribute ShippingAddress shippingAddress) {
        shippingAddress.setId(id);
        shippingAddressService.saveShippingAddress(shippingAddress);
        return "redirect:/shipping-addresses"; // Sau khi cập nhật, quay lại danh sách
    }

    // Xóa địa chỉ giao hàng
    @DeleteMapping("/{id}")
    public String deleteShippingAddress(@PathVariable Long id) {
        shippingAddressService.deleteShippingAddress(id);
        return "redirect:/shipping-addresses"; // Sau khi xóa, quay lại danh sách
    }
}
