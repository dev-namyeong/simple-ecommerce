package com.example.demo.cartitem;

import com.example.demo.product.Product;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 사용자 참조

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; // 상품 참조

    private int quantity; // 수량

    public CartItem(User user, Product product, int quantity){
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public void changeQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
}
