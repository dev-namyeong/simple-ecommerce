package com.example.demo.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // 상위 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    // 하위 카테고리 목록
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("sortOrder ASC")
    private List<Category> children = new ArrayList<>();

    // 정렬 순서 (선택)
    private int sortOrder = 0;

    // 카테고리 깊이 : 0(최상위), 1, 2 ...
    private int depth;

    public Category(String name, Category parent, int depth, int sortOrder){
        this.name = name;
        this.parent = parent;
        this.depth = depth;
        this.sortOrder = sortOrder;
    }
}
