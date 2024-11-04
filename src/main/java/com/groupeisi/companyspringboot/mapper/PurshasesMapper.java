package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.PurshaseDto;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;

import java.util.List;

public interface PurshasesMapper {

    PurshasesEntity toPurchasesEntity(PurshaseDto purchasesDto);

    PurshaseDto toPurchasesDto(PurshaseDto purshasesEntity);

    List<PurshaseDto> toListPurchasesDto(List<PurshasesEntity> purchasesEntities);
}
