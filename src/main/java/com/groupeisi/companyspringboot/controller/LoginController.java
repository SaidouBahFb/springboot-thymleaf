package com.groupeisi.companyspringboot.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/login")
    public String login() {
        return "index";
    }

    @GetMapping(value = "")
    public String home() {
        return "redirect:/logon";
    }

    @GetMapping(value = "/logon")
    public String logon(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/welcome";
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request) {

        logger.info("Error");

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }

        return "error";
    }
}
