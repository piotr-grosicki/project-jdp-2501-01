package com.kodilla.ecommercee.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @GetMapping
    public List<CartDto> getCarts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{cartId}")
    public CartDto getCart(@PathVariable Long CartId) {
        return new CartDto(1L, 1122);
    }

    @PutMapping
    public CartDto updateCart(CartDto cartDto) {
        return new CartDto(1L, 1221);
    }

    @PostMapping
    public void postCart(CartDto cartDto) {
    }

    @DeleteMapping
    public void deleteCart(Long CartId){
    }
}
