package com.example.websitefood.services;

import com.example.websitefood.entities.Wishlist;
import com.example.websitefood.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll(); // Lấy toàn bộ danh sách Wishlist
    }

    public Wishlist getWishlistById(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist không tồn tại với ID: " + id));
    }

    public void saveWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist); // Lưu mới hoặc cập nhật Wishlist
    }

    public void updateWishlist(Long id, Wishlist wishlist) {
        wishlist.setId(id); // Đảm bảo ID không thay đổi khi cập nhật
        wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id); // Xóa Wishlist theo ID
    }
}
