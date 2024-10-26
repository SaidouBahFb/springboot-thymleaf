package com.groupeisi.companyspringboot.enties;

import jakarta.persistence.*;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "date_p", nullable = false)
    private Date dateP;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "product_ref", nullable = false)
    private ProductEntity product;
}
