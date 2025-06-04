package com.example.demo.productImage;

import com.example.demo.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private Boolean isThumbnail = false; // 썸네일 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(String imageUrl, boolean isThumbnail){
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
