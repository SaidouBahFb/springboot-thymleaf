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
public class SalesRequestDto {
    @NotNull(message = "La date d'achat est obligatoire.")
    private Date dateP;

    @Min(value = 0, message = "Le stock ne peut pas être négatif.")
    private double quantity;

    @NotBlank(message = "La référence du produit est obligatoire.")
    private String productRef;
}
