package com.kodbook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String index(Model model, String error) {
        model.addAttribute("error", error != null);
        return "redirect:/web/login";
    }
}
