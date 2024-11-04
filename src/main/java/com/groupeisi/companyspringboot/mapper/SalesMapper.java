package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.SalesDto;
import com.groupeisi.companyspringboot.enties.SalesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    SalesEntity toSalesEntity(SalesDto salesDto);

    SalesDto toSalesDto(SalesEntity salesEntity);

    List<SalesDto> toListSalesDto(List<SalesEntity> salesEntities);
}
