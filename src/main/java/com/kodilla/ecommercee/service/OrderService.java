package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders(){
        return orderRepository.findAll().stream().map(orderMapper::maptoOrderDto).collect(Collectors.toList());
    }
    public OrderDto getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.maptoOrderDto(order);
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto){
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = orderMapper.maptoOrder(orderDto, user, cart);
        order = orderRepository.save(order);
        return orderMapper.maptoOrderDto(order);
    }
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto, Long orderId){
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setStatus(orderDto.getStatus());
        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.maptoOrderDto(updatedOrder);
    }
}
