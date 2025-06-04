package com.example.demo.category;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNullOrderBySortOrder(); // 상위 카테고리

    List<Category> findByParentIdOrderBySortOrder(Long parentId); // 특정 부모의 자식
}
