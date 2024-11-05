package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.request.ProductRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductEntity toProductEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toProductResponseDto(ProductEntity productEntity);

    List<ProductResponseDto> toListProductResponseDto(List<ProductEntity> productEntities);
}
