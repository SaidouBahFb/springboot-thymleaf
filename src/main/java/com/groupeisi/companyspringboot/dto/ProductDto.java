package com.groupeisi.companyspringboot.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String ref;

    private String name;

    private double stock;
}
