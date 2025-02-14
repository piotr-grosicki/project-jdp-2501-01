package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @GetMapping
    public List<String> getCarts() {
        return List.of("kodilla","kodilla2");
    }

    @GetMapping(value = "/{cartId}")
    public String getCart(@PathVariable String cartId) {
        return "wartosc" + cartId;
    }

    @PutMapping(value = "/{cartId}")
    public String updateCart(@RequestBody String cartId) {
        return "wartosc2";
    }

    @PostMapping
    public void postCart(@RequestBody String cartId) {
    }

    @DeleteMapping(value = "/{cartId}")
    public void deleteCart(@PathVariable String cartId){
    }
}
