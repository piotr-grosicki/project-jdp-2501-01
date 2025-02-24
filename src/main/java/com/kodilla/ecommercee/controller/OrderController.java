package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class OrderController {

    private final OrderRepository orderRepository;

@GetMapping
    public List<Order> getOrdersList() {
    return orderRepository.findAll();
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
    return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
    order.setId(null);
    return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
    orderRepository.deleteById(orderId);
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
    return orderRepository.save(order);
    }
}