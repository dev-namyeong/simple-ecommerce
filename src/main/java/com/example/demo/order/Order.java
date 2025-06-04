package com.example.demo.order;

import com.example.demo.orderitem.OrderItem;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Integer totalPrice; // 총 결제금액

    @Enumerated(EnumType.STRING)
    private Status status = Status.READY; // 주문 상태

    private LocalDateTime orderedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public enum Status{
        READY,PAID,SHIPPED,CANCELLED
    }

    public Order(User user, Integer totalPrice){
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public void addItem(OrderItem item){
        item.setOrder(this);
        items.add(item);
    }

    public void updateStatus(Status status){
        this.status = status;
    }
}
