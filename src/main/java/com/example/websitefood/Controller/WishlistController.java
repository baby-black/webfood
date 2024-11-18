package com.example.websitefood.Controller;

import com.example.websitefood.entities.Wishlist;
import com.example.websitefood.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Hiển thị danh sách Wishlist
    @GetMapping
    public String listWishlists(Model model) {
        model.addAttribute("wishlists", wishlistService.getAllWishlists());
        return "Wishlist/wishlist_list"; // Kết nối với trang wishlist_list.html
    }

    // Hiển thị chi tiết một Wishlist theo ID
    @GetMapping("/detail/{id}")
    public String wishlistDetail(@PathVariable Long id, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        model.addAttribute("wishlist", wishlist);
        return "Wishlist/wishlist_detail"; // Kết nối với trang wishlist_detail.html
    }

    // Hiển thị form thêm mới Wishlist
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        return "Wishlist/wishlist_form"; // Kết nối với trang wishlist_form.html
    }

    // Lưu một Wishlist mới
    @PostMapping
    public String saveWishlist(@ModelAttribute Wishlist wishlist) {
        wishlistService.saveWishlist(wishlist);
        return "redirect:/wishlists"; // Chuyển hướng về danh sách
    }

    // Hiển thị form chỉnh sửa một Wishlist
    @GetMapping("/edit/{id}")
    public String editWishlist(@PathVariable Long id, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        model.addAttribute("wishlist", wishlist);
        return "Wishlist/wishlist_form"; // Kết nối với trang wishlist_form.html
    }

    // Cập nhật Wishlist
    @PostMapping("/update/{id}")
    public String updateWishlist(@PathVariable Long id, @ModelAttribute Wishlist wishlist) {
        wishlistService.updateWishlist(id, wishlist);
        return "redirect:/wishlists"; // Chuyển hướng về danh sách
    }

    // Xóa Wishlist
    @GetMapping("/delete/{id}")
    public String deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return "redirect:/wishlists"; // Chuyển hướng về danh sách
    }

    // Hiển thị một trang tùy chỉnh (view chi tiết hoặc thống kê, v.v.)
    @GetMapping("/view")
    public String viewWishlist(Model model) {
        model.addAttribute("wishlists", wishlistService.getAllWishlists());
        return "Wishlist/wishlist_view"; // Kết nối với trang wishlist_view.html
    }
}
