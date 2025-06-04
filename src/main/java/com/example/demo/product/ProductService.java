package com.example.demo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    // 관리자용 : 전체 상품 조회
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    // 사용자용 : 활성 상품만 조회
    public List<Product> findActiveProducts(){
        return productRepository.findByIsActiveTrue();
    }

    // 상품 단건 조회
    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품을 찾을 수 없습니다. id=" + id));
    }

    // 상품 등록
    @Transactional
    public Product create(Product product){
        return productRepository.save(product);
    }

    // 상품 정보 수정
    @Transactional
    public void update(Long id, String name, String description, int price, int stock){
        Product product = findById(id);
        product.update(name, description, price, stock);
    }

    // 상품 삭제 (비활성화로 처리)
    @Transactional
    public void deactivate(Long id){
        Product product = findById(id);
        product.deactivate();
    }

    // 상품 다시 활성화
    @Transactional
    public void activate(Long id){
        Product product = findById(id);
        product.activate();
    }
}
