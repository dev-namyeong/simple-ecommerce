package com.example.demo.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductForm {

    @NotBlank(message = "상품명을 입력해주세요.")
    private String name;

    private String description;

    @NotNull(message = "가격을 입력해주세요.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Integer price;

    @NotNull(message = "재고를 입력해주세요.")
    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private Integer stock;

    // 이미지 파일 받는 필드 추가
    private List<MultipartFile> imageFiles = new ArrayList<>();

    public ProductForm(String name, String description, Integer price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
