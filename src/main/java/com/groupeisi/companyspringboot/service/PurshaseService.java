package com.groupeisi.companyspringboot.service;

import com.groupeisi.companyspringboot.dto.request.PurshaseRequestDto;
import com.groupeisi.companyspringboot.dto.response.PurshaseResponseDto;

import java.util.List;
import java.util.Optional;

public interface PurshaseService {
    Optional<PurshaseResponseDto> save(PurshaseRequestDto purshaseRequestDto);
    Optional<List<PurshaseResponseDto>> findAll();
}
