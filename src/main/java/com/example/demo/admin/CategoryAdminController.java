package com.example.demo.admin;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryForm;
import com.example.demo.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    // 카테고리 등록 폼
    @GetMapping("/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("categories", categoryService.getRootCategories()); // 상위 카테고리 선택용
        return "admin/category-form";
    }

    // 카테고리 저장 처리
    @PostMapping
    public String createCategory(@ModelAttribute CategoryForm form) {
        Category parent = null;
        int depth = 0;

        if (form.getParentId() != null) {
            parent = categoryService.findById(form.getParentId()); // ← 예외 발생 가능
            depth = parent.getDepth() + 1;
        }

        Category newCategory = new Category(
                form.getName(),
                parent,
                depth,
                form.getSortOrder()
        );
        categoryService.save(newCategory);

        return "redirect:/admin/categories";
    }

    // 카테고리 목록
    @GetMapping
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.getRootCategories());
        return "admin/category-list";
    }
}
