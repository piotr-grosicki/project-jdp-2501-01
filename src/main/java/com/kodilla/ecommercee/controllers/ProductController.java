package com.kodilla.ecommercee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<String> getProducts() {
        return new ArrayList<>(List.of("Produkt 1", "Produkt 2", "Produkt 3", "Produkt 4"));
    }

    @GetMapping(value = "{productId}")
    public String getProduct(@PathVariable int productId) {
        return "Produkt o ID " + productId;
    }

    @GetMapping(params = {"name"})
    public List<String> findProductsByName(@RequestParam String name) {
        return new ArrayList<>(List.of(name));
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        return "Stworzono produkt";
    }

    @PutMapping
    public String updateProduct(@RequestBody String product) {
        return "Zmieniono produkt";
    }

    @DeleteMapping(value = "{productId}")
    public String deleteProduct(@PathVariable int productId) {
        return "Usunięto produkt o ID " + productId;
    }

}
