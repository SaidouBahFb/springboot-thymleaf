package com.groupeisi.companyspringboot.service;

import com.groupeisi.companyspringboot.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDto> save(ProductDto productDto);
    Optional<List<ProductDto>> findAll();
}
