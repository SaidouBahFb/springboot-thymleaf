package com.groupeisi.companyspringboot.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String login() {
        return "index";
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errors/403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/500";
            }
        }

        return "errors";
    }
}
