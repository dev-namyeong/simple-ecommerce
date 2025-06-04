package com.example.demo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductUserController {

    private final ProductService productService;

    @GetMapping
    public String productList(Model model){
        List<Product> products = productService.findActiveProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable long id, Model model){
        Product product = productService.findById(id);
        if(!Boolean.TRUE.equals(product.getIsActive())){
            return "error/404";
        }
        model.addAttribute("product",product);
        return "product/detail";
    }
}
