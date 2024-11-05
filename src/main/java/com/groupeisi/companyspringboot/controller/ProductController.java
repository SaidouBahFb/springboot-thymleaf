package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.request.ProductRequestDto;
import com.groupeisi.companyspringboot.dto.response.ProductResponseDto;
import com.groupeisi.companyspringboot.service.ProductService;
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
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping(value = "/all")
    public String all(Model model) {
        try {
            Optional<List<ProductResponseDto>> products = productService.findAll();

            model.addAttribute("products", products.orElse(new ArrayList<>()));
            logger.info("ProductController-all: produits récupérés avec succès");
        }catch (Exception e){
            logger.error("ProductController-save Erreur lors de la récupération des produits", e);
        }

        model.addAttribute("product", new ProductRequestDto());

        return "products/all";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("product") ProductRequestDto productRequestDto, RedirectAttributes redirectAttributes) {
        logger.info("reference du produit : {}, nom du produit : {}", productRequestDto.getRef(), productRequestDto.getName());

        try {
            Optional<ProductResponseDto> savedProduct = productService.save(productRequestDto);
            redirectAttributes.addFlashAttribute("successMessage", "Produit enregistré avec succès.");
            logger.info("Produit enregistré : {}", savedProduct);
        }catch (Exception e){
            logger.error("Erreur lors de l'enregistrement du produit : {}", productRequestDto, e);
        }

        return "redirect:/products/all";
    }
}
