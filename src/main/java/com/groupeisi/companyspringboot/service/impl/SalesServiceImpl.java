package com.groupeisi.companyspringboot.service.impl;

import com.groupeisi.companyspringboot.dao.ProductRepository;
import com.groupeisi.companyspringboot.dao.SalesRepository;
import com.groupeisi.companyspringboot.dto.request.SalesRequestDto;
import com.groupeisi.companyspringboot.dto.response.SalesResponseDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import com.groupeisi.companyspringboot.enties.SalesEntity;
import com.groupeisi.companyspringboot.exceptions.EntityNotFoundException;
import com.groupeisi.companyspringboot.exceptions.InsufficientStockException;
import com.groupeisi.companyspringboot.mapper.SalesMapper;
import com.groupeisi.companyspringboot.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {
    private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final SalesMapper salesMapper;

    @Override
    public Optional<SalesResponseDto> save(SalesRequestDto salesRequestDto) {
        Optional<ProductEntity> productEntityOptional = productRepository.findByRef(salesRequestDto.getProductRef());

        if (productEntityOptional.isEmpty()) {
            logger.warn("SalesServiceImpl-save: Le produit avec la référence {} n'existe pas.", salesRequestDto.getProductRef());
            throw new EntityNotFoundException("Produit non trouvé avec la référence : " + salesRequestDto.getProductRef());
        }
        ProductEntity productEntity = productEntityOptional.get();
        double stockAvailable = productEntity.getStock();
        double saleQuantity = salesRequestDto.getQuantity();

        if (stockAvailable < saleQuantity) {
            logger.warn("SalesServiceImpl-save: Quantité insuffisante pour le produit ref : {}. Stock disponible : {}, Quantité demandée : {}",
                    salesRequestDto.getProductRef(), stockAvailable, saleQuantity);
            throw new InsufficientStockException("Stock insuffisant pour le produit ref : " + salesRequestDto.getProductRef());
        }

        double newQuantity = stockAvailable - saleQuantity;
        productEntity.setStock(newQuantity);
        productRepository.save(productEntity);

        logger.info("SalesServiceImpl-save: Quantité mise à jour pour le produit ref : {}. Nouvelle quantité : {}",
                salesRequestDto.getProductRef(), newQuantity);

        SalesEntity sales = salesMapper.toSalesEntity(salesRequestDto);

        sales.setQuantity(saleQuantity);
        sales.setProduct(productEntity);
        sales.setDateP(new Date());

        SalesEntity savedSalesEntity =  salesRepository.save(sales);

        logger.info("SalesServiceImpl-save: Vente enregistrée avec succès, ID de la vente : {}", savedSalesEntity.getId());

        return Optional.of(salesMapper.toSalesResponseDto(savedSalesEntity));
    }

    @Override
    public Optional<List<SalesResponseDto>> findAll() {
        return Optional.of(salesMapper.toListSalesResponseDto(salesRepository.findAll()));
    }
}
