package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @GetMapping
    public List<String> getCarts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{cartId}")
    public String getCart(@PathVariable Long CartId) {
        return "wartosc";
    }

    @PutMapping
    public String updateCart(@RequestBody String cartDto) {
        return "wartosc";
    }

    @PostMapping
    public void postCart(@RequestBody String cartDto) {
    }

    @DeleteMapping
    public void deleteCart(@PathVariable Long CartId){
    }
}
