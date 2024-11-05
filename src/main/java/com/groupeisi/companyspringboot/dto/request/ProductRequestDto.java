package com.groupeisi.companyspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "Le nom du produit est obligatoire.")
    private String ref;

    @NotBlank(message = "La référence du produit est obligatoire.")
    private String name;

    @Min(value = 0, message = "Le stock ne peut pas être négatif.")
    private double stock;
}
