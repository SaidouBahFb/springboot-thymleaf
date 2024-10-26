package com.groupeisi.companyspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }
}
