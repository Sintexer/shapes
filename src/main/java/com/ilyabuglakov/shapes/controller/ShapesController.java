package com.ilyabuglakov.shapes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shapes")
public class ShapesController {

    @GetMapping
    public String home(@RequestParam(required = false, defaultValue = "World") String param, Model model){
        model.addAttribute("word", param);
        return "home";
    }
}
