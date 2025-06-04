package com.example.demo.product;

import com.example.demo.file.FileStorageService;
import com.example.demo.productImage.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    // 상품 목록 페이지
    @GetMapping
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    // 상품 등록 폼
    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "admin/form";
    }

    // 상품 등록 처리
    @PostMapping
    public String create(@Valid @ModelAttribute ProductForm productForm, BindingResult result) throws IOException {
        if(result.hasErrors()){
            return "admin/form";
        }
        Product product = new Product(
                productForm.getName(),
                productForm.getDescription(),
                productForm.getPrice(),
                productForm.getStock()
        );

        // 이미지 저장 처리
        for (MultipartFile file : productForm.getImageFiles()) {
            if (!file.isEmpty()) {
                String storedFileName = fileStorageService.storedFile(file, "product_");
                ProductImage image = new ProductImage("/images/product-images/" + storedFileName, false);
                product.addImage(image);
            }
        }

        productService.create(product);
        return "redirect:/admin/products";
    }

    // 상품 수정 폼
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model){
        Product product = productService.findById(id);
        ProductForm form = new ProductForm(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
        model.addAttribute("productForm", form);
        model.addAttribute("productId", id);
        model.addAttribute("productImages",product.getImages());
        return "admin/form";
    }

    // 상품 수정 처리
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute ProductForm productForm, BindingResult result){
        if(result.hasErrors()){
            return "admin/form";
        }
        productService.update(
                id,
                productForm.getName(),
                productForm.getDescription(),
                productForm.getPrice(),
                productForm.getStock()
        );
        return "redirect:/admin/products";
    }

    // 상품 비활성화
    @PostMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id){
        productService.deactivate(id);
        return "redirect:/admin/products";
    }

    // 상품 활성화
    @PostMapping("/{id}/activate")
    public String activate(@PathVariable Long id){
        productService.activate(id);
        return "redirect:/admin/products";
    }

    // 상품 상세 페이지
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/product-detail";
    }
}
