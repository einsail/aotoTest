package com.example.apitesting.web;

import com.example.apitesting.config.EnvironmentProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final EnvironmentProperties environmentProperties;

    public ViewController(EnvironmentProperties environmentProperties) {
        this.environmentProperties = environmentProperties;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("baseUrl", environmentProperties.getBaseUrl());
        model.addAttribute("timeout", environmentProperties.getDefaultTimeout());
        return "index";
    }
}
