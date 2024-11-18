package com.example.websitefood.Controller;

import com.example.websitefood.entities.Payment;
import com.example.websitefood.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Hiển thị danh sách thanh toán
    @GetMapping
    public String listPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payment/payment_list";
    }

    // Hiển thị form thêm thanh toán mới
    @GetMapping("/new")
    public String newPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "payment/payment_form";
    }

    // Lưu thanh toán mới
    @PostMapping("/save")
    public String savePayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    // Hiển thị form chỉnh sửa thanh toán
    @GetMapping("/{id}/edit")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            model.addAttribute("payment", payment);
            return "payment/payment_form";
        } else {
            return "redirect:/payments";
        }
    }

    // Cập nhật thanh toán
    @PostMapping("/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Payment payment) {
        payment.setId(id); // Cập nhật ID trước khi lưu
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    // Xóa thanh toán
    @GetMapping("/{id}/delete")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}
