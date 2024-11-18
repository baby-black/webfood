package com.example.websitefood.Controller;

import com.example.websitefood.entities.User;
import com.example.websitefood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Hiển thị danh sách người dùng
    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/user_list";  // Tên này phải khớp với tên template
    }

    // Hiển thị chi tiết người dùng
    @GetMapping("/{id}")
    public String viewUser(@PathVariable long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.orElse(null));
        return "user/user_detail"; // Không cần ".html"
    }

    // Form tạo người dùng mới
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User()); // Sử dụng đúng class User
        return "user/user_form"; // Không cần ".html"
    }

    // Tạo mới người dùng từ form
    @PostMapping("/new")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users"; // Sau khi tạo xong, chuyển hướng về danh sách
    }

    // Cập nhật người dùng
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.orElse(null));
        return "user/user_form"; // Không cần ".html"
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/users"; // Sau khi cập nhật xong, chuyển hướng về danh sách
    }

    // Xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Sau khi xóa xong, chuyển hướng về danh sách
    }
}
