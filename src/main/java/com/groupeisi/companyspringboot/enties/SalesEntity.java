package com.groupeisi.companyspringboot.enties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
public class SalesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "La date de vente est obligatoire.")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_p", nullable = false)
    private Date dateP;

    @Min(value = 0, message = "La quantité ne peut pas être négative.")
    @Column(name = "quantity", nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "product_ref", nullable = false)
    private ProductEntity product;
}
