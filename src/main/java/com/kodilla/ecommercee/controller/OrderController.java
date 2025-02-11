package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<String> getOrdersList() {
        return List.of("zamówienie1", "zamówienie2", "zamówienie3");
    }

    @GetMapping("{orderId}")
    public String getOrder(@PathVariable String orderId) {
        return "Twoje zamówienie to: " + orderId;
    }

    @PostMapping
    public String createOrder(String order) {
        return "zamówienie zostało utworzone";
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable String orderId) {
        return "zamówienie " + orderId + " zostało usunięte";
    }

    @PutMapping
    public String updateOrder(String order) {
        return "Zamówienie zostało zaktualizowane";
    }
}