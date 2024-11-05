package com.groupeisi.companyspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private String ref;
    private String name;
    private double stock;
}
