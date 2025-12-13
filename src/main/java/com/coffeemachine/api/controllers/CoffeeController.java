package com.coffeemachine.api.controllers;

import com.coffeemachine.api.models.Coffee;
import com.coffeemachine.api.services.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // GET /coffees - list all (or we could return only available)
    @GetMapping
    public ResponseEntity<List<Coffee>> getAll() {
        return ResponseEntity.ok(coffeeService.listAll());
    }

    // GET /coffees/available - convenience endpoint
    @GetMapping("/available")
    public ResponseEntity<List<Coffee>> getAvailable() {
        return ResponseEntity.ok(coffeeService.listAvailable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return coffeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
