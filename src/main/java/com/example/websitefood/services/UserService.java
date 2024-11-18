package com.example.websitefood.services;

import com.example.websitefood.entities.User;
import com.example.websitefood.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    // Tạo mới người dùng
    public User createUser(User user) {
        // Có thể thêm mã hóa mật khẩu ở đây nếu cần
        user.setCreatedAt(new java.util.Date());
        user.setUpdatedAt(new java.util.Date());
        return userRepository.save(user);
    }

    // Cập nhật thông tin người dùng
    public User updateUser(long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setUpdatedAt(new java.util.Date());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    // Xóa người dùng theo ID
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
