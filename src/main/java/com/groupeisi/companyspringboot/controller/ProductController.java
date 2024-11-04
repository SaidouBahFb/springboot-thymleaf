package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/all")
    public String all(Model model) {
        model.addAttribute("product", new ProductDto());
        return "products/all";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("product") ProductDto product) {
        logger.info("reference du produit : {}, nom du produit : {}", product.getRef(), product.getName());
        return "products/all";
    }
}
