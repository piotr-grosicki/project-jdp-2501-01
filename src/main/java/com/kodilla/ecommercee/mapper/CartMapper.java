package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getProducts().stream().map(Product::getId).collect(Collectors.toList())
        );
    }

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(),
                cartDto.getUserId(),
                cartDto.getProductId());
    }
}
