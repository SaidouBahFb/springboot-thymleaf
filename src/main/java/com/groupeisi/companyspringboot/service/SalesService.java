package com.groupeisi.companyspringboot.service;

import com.groupeisi.companyspringboot.dto.request.SalesRequestDto;
import com.groupeisi.companyspringboot.dto.response.SalesResponseDto;

import java.util.List;
import java.util.Optional;

public interface SalesService {
    Optional<SalesResponseDto> save(SalesRequestDto salesRequestDto);
    Optional<List<SalesResponseDto>> findAll();
}
