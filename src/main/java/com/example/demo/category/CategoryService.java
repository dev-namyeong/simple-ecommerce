package com.example.demo.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getRootCategories(){
        return categoryRepository.findByParentIsNullOrderBySortOrder();
    }

    public List<Category> getChildren(Long parentId){
        return categoryRepository.findByParentIdOrderBySortOrder(parentId);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }
}
