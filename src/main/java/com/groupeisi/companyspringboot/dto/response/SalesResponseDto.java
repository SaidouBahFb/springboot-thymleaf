package com.groupeisi.companyspringboot.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesResponseDto {
    private long id;
    private Date dateP;
    private double quantity;
    private ProductResponseDto product;
}
