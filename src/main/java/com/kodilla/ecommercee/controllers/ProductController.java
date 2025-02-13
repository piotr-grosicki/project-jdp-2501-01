package com.kodilla.ecommercee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<String> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public String getProduct(@PathVariable int productId) {

        return "products";
    }

    @GetMapping
    public List<String> findProductsByName(@RequestParam String name) {
        return new ArrayList<>();
    }

    @PostMapping
    public void createProduct(@RequestBody Object product) {

    }

    @PutMapping
    public void updateProduct(@RequestBody Object product) {

    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable int productId) {

    }

}
