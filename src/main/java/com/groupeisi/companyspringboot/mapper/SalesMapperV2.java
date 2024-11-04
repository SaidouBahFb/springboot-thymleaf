package com.groupeisi.companyspringboot.mapper;

import com.groupeisi.companyspringboot.dto.SalesDto;
import com.groupeisi.companyspringboot.enties.SalesEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface SalesMapperV2 {
     static SalesEntity toSalesEntity(SalesDto salesDto) {
        SalesEntity sales = new SalesEntity();
        sales.setId(salesDto.getId());
        sales.setDateP(salesDto.getDateP());
        sales.setQuantity(salesDto.getQuantity());
       // sales.setProduct(ProductMapper.toProductEntity(salesDto.getProduct()));
        return sales;
    }

    static SalesDto toSalesDto(SalesEntity sales) {
        SalesDto salesDto = new SalesDto();
        salesDto.setId(sales.getId());
        salesDto.setDateP(sales.getDateP());
        salesDto.setQuantity(sales.getQuantity());
        //salesDto.setProduct(ProductMapper.toProductDto(sales.getProduct()));
        return salesDto;
    }

    static List<SalesDto> toListSalesDto(List<SalesEntity> salesList) {
        return salesList.stream()
                .map(SalesMapperV2::toSalesDto)
                .collect(Collectors.toList());
    }
}
