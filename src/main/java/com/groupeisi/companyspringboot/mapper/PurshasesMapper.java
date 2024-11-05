package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.request.PurshaseRequestDto;
import com.groupeisi.companyspringboot.dto.response.PurshaseResponseDto;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PurshasesMapper {
    PurshasesEntity toPurchasesEntity(PurshaseRequestDto purshaseRequestDto);

    PurshaseResponseDto toPurchasesResponseDto(PurshasesEntity purshasesEntity);

    List<PurshaseResponseDto> toListPurchasesResponseDto(List<PurshasesEntity> purchasesEntities);
}
