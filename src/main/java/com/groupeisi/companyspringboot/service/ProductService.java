package com.groupeisi.companyspringboot.service;

import com.groupeisi.companyspringboot.dto.request.ProductRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductResponseDto> save(ProductRequestDto productRequestDto);
    Optional<List<ProductResponseDto>> findAll();
}
