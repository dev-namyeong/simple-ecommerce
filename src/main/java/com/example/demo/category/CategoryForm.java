package com.example.demo.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {

    private String name; // 카테고리 이름
    private Long parentId; // 상위 카테고리 ID (없으면 null)
    private int sortOrder = 0; // 정렬 순서
}
