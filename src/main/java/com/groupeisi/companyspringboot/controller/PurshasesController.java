package com.groupeisi.companyspringboot.controller;

import com.groupeisi.companyspringboot.dto.PurshaseDto;
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
@RequestMapping("/purshases")
public class PurshasesController {
    private final Logger logger = LoggerFactory.getLogger(PurshasesController.class);


    @GetMapping(value = "/all")
    public String all(Model model) {
        model.addAttribute("purshase", new PurshaseDto());
        return "purshases/all";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("purshase") PurshaseDto purshase) {
        logger.info("reference du produit : {}, quantité du produit : {}", purshase.getProductRef(), purshase.getQuantity());
        return "purshases/all";
    }
}
