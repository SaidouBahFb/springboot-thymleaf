package com.groupeisi.companyspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurshaseRequestDto {
    @NotNull(message = "La date d'achat est obligatoire.")
    private Date dateP;

    @Min(value = 0, message = "La quantité ne peut pas être négative.")
    private double quantity;

    @NotBlank(message = "La référence du produit est obligatoire.")
    private String productRef;
}
