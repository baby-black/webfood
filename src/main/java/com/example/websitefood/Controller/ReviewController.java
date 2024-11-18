package com.example.websitefood.Controller;

import com.example.websitefood.entities.Review;
import com.example.websitefood.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Hiển thị danh sách đánh giá
    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "review/review_list";
    }

    // Hiển thị form thêm đánh giá mới
    @GetMapping("/new")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "review/review_form";
    }

    // Lưu đánh giá mới
    @PostMapping("/save")
    public String saveReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    // Hiển thị form chỉnh sửa đánh giá
    @GetMapping("/{id}/edit")
    public String editReviewForm(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            model.addAttribute("review", review);
            return "review/review_form";
        } else {
            return "redirect:/reviews";
        }
    }

    // Cập nhật đánh giá
    @PostMapping("/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review review) {
        review.setId(id); // Cập nhật ID trước khi lưu
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    // Xóa đánh giá
    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}
