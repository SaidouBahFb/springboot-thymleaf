package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.PurshaseDto;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PurshasesMapper {

    private PurshasesMapper() {
    }

    public static PurshasesEntity toPurchasesEntity(PurshaseDto purchasesDto) {

        PurshasesEntity purchasesEntity = new PurshasesEntity();

        purchasesEntity.setId(purchasesDto.getId());
        purchasesEntity.setDateP(purchasesDto.getDateP());
        purchasesEntity.setQuantity(purchasesDto.getQuantity());
        purchasesEntity.setProduct(ProductMapper.toProductEntity(purchasesDto.getProduct()));

        return purchasesEntity;
    }

    public static PurshaseDto toPurchasesDto(PurshaseDto purshasesEntity) {

        PurshaseDto purchasesDto = new PurshaseDto();

        purchasesDto.setId(purshasesEntity.getId());
        purchasesDto.setDateP(purshasesEntity.getDateP());
        purchasesDto.setQuantity(purshasesEntity.getQuantity());
        purchasesDto.setProduct(ProductMapper.toProductDto(purshasesEntity.getProduct()));

        return purchasesDto;
    }

    public static List<PurshaseDto> toListPurchasesDto(List<PurshasesEntity> purchasesEntities) {
        return purchasesEntities.stream()
                .map(PurshasesMapper::toPurchasesDto)
                .collect(Collectors.toList());
    }
}
