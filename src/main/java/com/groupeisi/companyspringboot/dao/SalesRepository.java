package com.groupeisi.companyspringboot.dao;

import com.groupeisi.companyspringboot.enties.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}
