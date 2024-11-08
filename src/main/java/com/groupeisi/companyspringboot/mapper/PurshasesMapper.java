package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.PurshaseDto;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PurshasesMapper {

    PurshasesEntity toPurchasesEntity(PurshaseDto purchasesDto);

    @Mapping(target = "productRef", source = "product.ref")
    PurshaseDto toPurchasesDto(PurshasesEntity purshasesEntity);

    List<PurshaseDto> toListPurchasesDto(List<PurshasesEntity> purchasesEntities);
}
