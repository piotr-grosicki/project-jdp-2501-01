package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;

    @GetMapping
    public List<CartDto> getCarts() {
        return cartService.getAllCarts().stream()
                .map(cartMapper::mapToCartDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{cartId}")
    public Optional<CartDto> getCart(@PathVariable Long cartId) {
        return cartService.getCartById(cartId).map(cartMapper::mapToCartDto);
    }

    @PutMapping(value = "/{cartId}")
    public CartDto updateCart(@PathVariable Long cartId, @RequestBody CartDto cartDto) {
        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartMapper.mapToCartDto(cartService.updateCart(cartId, user));
    }

    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(cartService.saveCart(cartMapper.mapToCart(cartDto)));
    }

    @DeleteMapping(value = "/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }
}
