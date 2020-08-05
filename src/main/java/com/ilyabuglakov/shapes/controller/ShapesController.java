package com.ilyabuglakov.shapes.controller;

import com.ilyabuglakov.shapes.entity.ShapeEntity;
import com.ilyabuglakov.shapes.model.shape.Shape;
import com.ilyabuglakov.shapes.repository.ShapesCrudRepository;
import com.ilyabuglakov.shapes.service.ShapeService;
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
    @Autowired
    ShapeService service;

    @GetMapping
    public String home(Model model){
        model.addAttribute("shapes", repository.getAllByIdNotNull());
        return "home";
    }

    @GetMapping("/add-shape")
    public String addShape(Model model){
        return "add-shape";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShape(@RequestBody Shape shape){
        shape = service.removeExcessPoints(shape);
        if(!shape.getPoints().isEmpty())
            repository.save(ShapeEntity.from(shape));
        return ResponseEntity.ok("Added "+ shape);
    }
}
