package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto maptoOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getCart().getId(),
                order.getStatus()
                ,order.getCreatedAt()
        );
    }
    public Order maptoOrder(OrderDto orderDto, User user, Cart cart) {
        return Order.builder()
                .id(orderDto.getId())
                .user(user)
                .cart(cart)
                .status(orderDto.getStatus())
                .createdAt(orderDto.getCreatedAt())
                .build();
    }
}
