package com.groupeisi.companyspringboot.service.impl;

import com.groupeisi.companyspringboot.dao.ProductRepository;
import com.groupeisi.companyspringboot.dto.request.ProductRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import com.groupeisi.companyspringboot.exceptions.DuplicateEntityException;
import com.groupeisi.companyspringboot.mapper.ProductMapper;
import com.groupeisi.companyspringboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Optional<ProductResponseDto> save(ProductRequestDto productRequestDto) {
        Optional<ProductEntity> existingProduct = productRepository.findByRef(productRequestDto.getRef());
        if (existingProduct.isPresent()) {
            logger.warn("ProductServiceImpl-save : La référence du produit {} existe déjà.", productRequestDto.getRef());
            throw new DuplicateEntityException("La référence du produit existe déjà : " + productRequestDto.getRef());
        }
        ProductEntity productEntity = productMapper.toProductEntity(productRequestDto);
        productEntity = productRepository.save(productEntity);
        logger.info("Produit enregistré avec succès : {}", productEntity);
        return Optional.of(productMapper.toProductResponseDto(productEntity));
    }

    @Override
    public Optional<List<ProductResponseDto>> findAll() {
        List<ProductEntity> products = productRepository.findAll();
        return Optional.of(productMapper.toListProductResponseDto(products));
    }
}
