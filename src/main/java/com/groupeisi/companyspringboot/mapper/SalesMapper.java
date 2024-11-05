package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.request.SalesRequestDto;
import com.groupeisi.companyspringboot.dto.response.SalesResponseDto;
import com.groupeisi.companyspringboot.enties.SalesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    SalesEntity toSalesEntity(SalesRequestDto salesRequestDto);

    SalesResponseDto toSalesResponseDto(SalesEntity salesEntity);

    List<SalesResponseDto> toListSalesResponseDto(List<SalesEntity> salesEntities);
}
