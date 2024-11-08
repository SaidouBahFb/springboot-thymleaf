package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.ProductDto;
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
        Optional<List<ProductDto>> optionalProductDto = productService.findAll();
        optionalProductDto.ifPresent(productDtos -> model.addAttribute("products", productDtos));
        model.addAttribute("product", new ProductDto());
        return "products/all";
    }

    @PostMapping(value = "/save")
    public String save(Model model, @ModelAttribute("product") ProductDto product) {
        productService.save(product);
        logger.info("reference du produit : {}, nom du produit : {}", product.getRef(), product.getName());
        Optional<List<ProductDto>> optionalProductDto = productService.findAll();
        optionalProductDto.ifPresent(productDtos -> model.addAttribute("products", productDtos));
        return "products/all";
    }
}
