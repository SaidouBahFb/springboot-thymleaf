package com.groupeisi.companyspringboot.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Id
    @NotBlank(message = "La référence du produit est obligatoire.")
    private String ref;

    @NotBlank(message = "Le nom du produit est obligatoire.")
    private String name;

    @Min(value = 0, message = "Le stock ne peut pas être négatif.")
    private double stock;

    @OneToMany(mappedBy = "product")
    private List<PurshasesEntity> purchases;

    @OneToMany(mappedBy = "product")
    private List<SalesEntity> sales;
}
