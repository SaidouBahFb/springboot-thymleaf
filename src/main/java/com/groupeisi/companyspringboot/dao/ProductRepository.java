package com.groupeisi.companyspringboot.dao;

import com.groupeisi.companyspringboot.enties.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
