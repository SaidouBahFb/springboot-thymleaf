package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.ProductDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductEntity toProductEntity(ProductDto productDto);

    ProductDto toProductDto(ProductEntity productEntity);

    List<ProductDto> toListProductDto(List<ProductEntity> productEntities);
}
