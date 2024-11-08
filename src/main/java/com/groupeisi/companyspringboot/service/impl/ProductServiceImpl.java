package com.groupeisi.companyspringboot.service.impl;

import com.groupeisi.companyspringboot.dao.ProductRepository;
import com.groupeisi.companyspringboot.dto.ProductDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import com.groupeisi.companyspringboot.mapper.ProductMapper;
import com.groupeisi.companyspringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    public Optional<ProductDto> save(ProductDto productDto) {
        ProductEntity productEntity =  productMapper.toProductEntity(productDto);
        productEntity = productRepository.save(productEntity);

        return Optional.of(productMapper.toProductDto(productEntity));
    }

    @Override
    public Optional<List<ProductDto>> findAll() {
        return Optional.of(productMapper.toListProductDto(productRepository.findAll()));
    }
}
