package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }
    public Optional<Cart> getCartById(long id) {
        return cartRepository.findById(id);
    }
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public Cart updateCart(Long id, User changeUser) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setUser(changeUser);
            return cartRepository.save(cart);
        }
        throw new RuntimeException("Cart not found");
    }

    public void deleteCart(long id) {
        cartRepository.deleteById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

}
