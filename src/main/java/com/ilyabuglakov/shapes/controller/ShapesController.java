package com.ilyabuglakov.shapes.controller;

import com.ilyabuglakov.shapes.model.Shape;
import com.ilyabuglakov.shapes.entity.ShapeEntity;
import com.ilyabuglakov.shapes.repository.ShapesCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shapes")
public class ShapesController {

    @Autowired
    ShapesCrudRepository repository;

    @GetMapping
    public String home(@RequestParam(required = false, defaultValue = "World") String param, Model model){
        model.addAttribute("word", param);
        return "home";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShape(@RequestBody Shape shape){
        repository.save(ShapeEntity.from(shape));
        return ResponseEntity.ok("Added "+ shape);
    }
}
