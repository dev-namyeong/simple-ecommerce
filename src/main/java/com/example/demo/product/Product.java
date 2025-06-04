package com.example.demo.product;

import com.example.demo.category.Category;
import com.example.demo.productImage.ProductImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.category.Category;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private Integer price;

    private Integer stock;

    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    // 상품 이미지들
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    // 카테고리 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 생성자
    public Product(String name, String description, Integer price, Integer stock){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public void update(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public void activate(){
        this.isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }

    public void updatePrice(int newPrice){
        this.price = newPrice;
    }

    public void changeStock(int newStock){
        this.stock = newStock;
    }

    public void addImage(ProductImage image){
        image.setProduct(this);
        this.images.add(image);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
