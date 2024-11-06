package com.groupeisi.companyspringboot.service.impl;

import com.groupeisi.companyspringboot.dao.ProductRepository;
import com.groupeisi.companyspringboot.dao.PurshasesRepository;
import com.groupeisi.companyspringboot.dto.request.PurshaseRequestDto;
import com.groupeisi.companyspringboot.dto.response.PurshaseResponseDto;
import com.groupeisi.companyspringboot.enties.ProductEntity;
import com.groupeisi.companyspringboot.enties.PurshasesEntity;
import com.groupeisi.companyspringboot.exceptions.EntityNotFoundException;
import com.groupeisi.companyspringboot.mapper.PurshasesMapper;
import com.groupeisi.companyspringboot.service.PurshasesService;
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
public class PurshasesServiceImpl implements PurshasesService {
    private static final Logger logger = LoggerFactory.getLogger(PurshasesServiceImpl.class);

    private final PurshasesRepository purshasesRepository;
    private final ProductRepository productRepository;
    private final PurshasesMapper purshasesMapper;

    @Override
    public Optional<PurshaseResponseDto> save(PurshaseRequestDto purshaseRequestDto) {
        Optional<ProductEntity> productEntityOptional = productRepository.findByRef(purshaseRequestDto.getProductRef());

        if (productEntityOptional.isEmpty()) {
            logger.warn("PurshasesServiceImpl-save: Le produit avec la référence {} n'existe pas.", purshaseRequestDto.getProductRef());
            throw new EntityNotFoundException("Produit non trouvé avec la référence : " + purshaseRequestDto.getProductRef());
        }

        ProductEntity productEntity = productEntityOptional.get();
        double newQty = productEntity.getStock() + purshaseRequestDto.getQuantity();
        productEntity.setStock(newQty);

        productRepository.save(productEntity);

        logger.info("PurshasesServiceImpl-save: Quantité mise à jour pour le produit ref : {}. Nouvelle quantité : {}",
                purshaseRequestDto.getProductRef(), newQty);

        PurshasesEntity purshasesEntity = new PurshasesEntity();

        purshasesEntity.setProduct(productEntity);
        purshasesEntity.setQuantity(purshaseRequestDto.getQuantity());
        purshasesEntity.setDateP(new Date());

        PurshasesEntity savedPurshasesEntity = purshasesRepository.save(purshasesEntity);

        logger.info("PurshaseServiceImpl-save: Achat enregistré avec succès, ID de l'achat : {}", savedPurshasesEntity.getId());

        return Optional.of(purshasesMapper.toPurchasesResponseDto(savedPurshasesEntity));
    }

    @Override
    public Optional<List<PurshaseResponseDto>> findAll() {
        return Optional.of(purshasesMapper.toListPurchasesResponseDto(purshasesRepository.findAll()));
    }
}
