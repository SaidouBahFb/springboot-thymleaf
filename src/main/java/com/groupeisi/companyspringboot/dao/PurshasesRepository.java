package com.groupeisi.companyspringboot.dao;

import com.groupeisi.companyspringboot.enties.PurshasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurshasesRepository extends JpaRepository<PurshasesEntity, Long> {
}
