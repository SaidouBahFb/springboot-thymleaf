package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.request.PurshaseRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;
import com.groupeisi.companyspringboot.dto.response.PurshaseResponseDto;
import com.groupeisi.companyspringboot.service.ProductService;
import com.groupeisi.companyspringboot.service.PurshaseService;
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
@RequestMapping("/purshases")
@AllArgsConstructor
public class PurshasesController {
    private final Logger logger = LoggerFactory.getLogger(PurshasesController.class);
    private final ProductService productService;
    private final PurshaseService purshaseService;

    @GetMapping(value = "/all")
    public String all(Model model) {
        try {
            Optional<List<PurshaseResponseDto>> purshases = purshaseService.findAll();
            Optional<List<ProductResponseDto>> products = productService.findAll();

            model.addAttribute("purshases", purshases.orElse(new ArrayList<>()));
            model.addAttribute("products", products.orElse(new ArrayList<>()));
            logger.info("PurshasesController-all: produits et achats récupérés avec succès");
        } catch (Exception e) {
            logger.error("PurshasesController-save Erreur lors de la récupération des achats ou de produits", e);
        }

        model.addAttribute("purshase", new PurshaseRequestDto());
        return "purshases/all";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("purshase") PurshaseRequestDto purshaseRequestDto, RedirectAttributes redirectAttributes) {
        logger.info("reference du produit : {}, quantité du produit : {}", purshaseRequestDto.getProductRef(), purshaseRequestDto.getQuantity());

        try {
            Optional<PurshaseResponseDto> savedPurshase = purshaseService.save(purshaseRequestDto);
            redirectAttributes.addFlashAttribute("successMessage", "Achat enregistré avec succès.");
            logger.info("Achat enregistré : {}", savedPurshase);

        } catch (Exception e) {
            logger.error("Erreur lors de l'enregistrement de l'achat : {}", purshaseRequestDto, e);
        }
        return "redirect:/purshases/all";
    }
}
