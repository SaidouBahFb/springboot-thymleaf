package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.request.SalesRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;
import com.groupeisi.companyspringboot.dto.response.SalesResponseDto;
import com.groupeisi.companyspringboot.service.ProductService;
import com.groupeisi.companyspringboot.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/sales")
public class SalesController {
    private final Logger logger = LoggerFactory.getLogger(SalesController.class);
    private final SalesService salesService;
    private final ProductService productService;

    @GetMapping(value = "/all")
    public String all(Model model) {
        try {
            Optional<List<SalesResponseDto>> sales = salesService.findAll();
            Optional<List<ProductResponseDto>> products = productService.findAll();

            model.addAttribute("sales", sales.orElse(new ArrayList<>()));
            model.addAttribute("products", products.orElse(new ArrayList<>()));
            logger.info("SalesController-all: produits et ventes récupérés avec succès");
        }catch (Exception e) {
            logger.error("SalesController-all: Erreur lors de la récupération des ventes ou de produits", e);
        }
        model.addAttribute("sale", new SalesRequestDto());
        return "sales/all";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("sale") SalesRequestDto salesRequestDto, RedirectAttributes redirectAttributes) {
        logger.info("reference du produit : {}, quantité du produit : {}", salesRequestDto.getProductRef(), salesRequestDto.getQuantity());
        try {
            Optional<SalesResponseDto> savedSales = salesService.save(salesRequestDto);
            redirectAttributes.addFlashAttribute("successMessage", "Vente enregistré avec succès.");
            logger.info("Vente enregistré : {}", savedSales);
        }catch (Exception e) {
            logger.error("Erreur lors de l'enregistrement de la vente : {}", salesRequestDto, e);
        }
        return "redirect:/sales/all";
    }
}
