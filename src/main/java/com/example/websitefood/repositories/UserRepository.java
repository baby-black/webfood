package com.example.websitefood.repositories;

import com.example.websitefood.entities.User; // Đảm bảo rằng bạn đang import đúng User
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Bạn có thể thêm các phương thức truy vấn bổ sung ở đây nếu cần
}
