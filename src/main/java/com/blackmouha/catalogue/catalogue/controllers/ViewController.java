package com.blackmouha.catalogue.catalogue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class ViewController {
    @GetMapping("/products")
    public String home() {
        return "views/index";
    }
}
