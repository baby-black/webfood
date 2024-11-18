package com.example.websitefood.Controller;

import org.springframework.ui.Model;
import com.example.websitefood.entities.Product;
import com.example.websitefood.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/new")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/product_form";
    }

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/product_list"; // Kiểm tra đường dẫn đến file HTML trong thư mục resources/templates/product/
    }

    // Thêm sản phẩm mới
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products"; // Sau khi tạo sản phẩm mới, sẽ chuyển hướng về danh sách sản phẩm
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/product_form"; // Kiểm tra đường dẫn đến file HTML trong thư mục resources/templates/product/
        } else {
            return "redirect:/products"; // Nếu không tìm thấy sản phẩm, chuyển hướng về danh sách sản phẩm
        }
    }

    // Cập nhật sản phẩm
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id); // Cập nhật id của sản phẩm
        productService.saveProduct(product);
        return "redirect:/products"; // Sau khi cập nhật sản phẩm, chuyển hướng về danh sách sản phẩm
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products"; // Sau khi xóa sản phẩm, chuyển hướng về danh sách sản phẩm
    }
}
