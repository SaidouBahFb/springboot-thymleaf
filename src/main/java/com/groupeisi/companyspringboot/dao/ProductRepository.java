package com.groupeisi.companyspringboot.dao;

import com.groupeisi.companyspringboot.enties.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByRef(String ref);
}
