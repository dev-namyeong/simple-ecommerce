package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // 활성 상태의 상품만 조회
    List<Product> findByIsActiveTrue();

    // 상품명으로 부분 검색 (옵션)
    List<Product> findByNameContainingIgnoreCaseAndIsActiveTrue(String keyword);
}
