package com.groupeisi.companyspringboot.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurshaseDto {
    private long id;
    private Date dateP;
    private double quantity;
    private String productRef;
}
