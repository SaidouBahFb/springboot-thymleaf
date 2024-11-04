package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.PurshaseDto;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PurshasesMapper {

    PurshasesEntity toPurchasesEntity(PurshaseDto purchasesDto);

    PurshaseDto toPurchasesDto(PurshasesEntity purshasesEntity);

    List<PurshaseDto> toListPurchasesDto(List<PurshasesEntity> purchasesEntities);
}
