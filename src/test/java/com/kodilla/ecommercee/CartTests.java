package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CartTests {

    @MockBean
    private CartRepository cartRepository;


    @Test
    void shouldCreateCart(){

        //Given
        User user = User.builder()
                .id(1l)
                .username("jan123")
                .build();
        Cart cart = Cart.builder()
                .user(user)
                .build();

        when(cartRepository.save(any())).thenReturn(cart);

        //When

        Cart savedCart = cartRepository.save(cart);

        //Then
        assertEquals(user, savedCart.getUser());
        verify(cartRepository, times(1)).save(any());

    }

    @Test
    void shouldFindCart(){

        //Given
        User user = User.builder()
                .id(1l)
                .username("anna456")
                .build();
        Cart cart = Cart.builder()
                .user(user)
                .build();

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        //When
        Optional<Cart> foundCart = cartRepository.findById(1L);

        //Then
        assertTrue(foundCart.isPresent());
        assertEquals("anna456", foundCart.get().getUser().getUsername());
    }

    @Test
    void shouldUpdateCart(){

        //Given
        User user = User.builder()
                .id(1l)
                .username("jan123")
                .build();
        Product product = Product.builder()
                .name("Monitor")
                .price(1500.00)
                .build();
        Cart cart = Cart.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();

        when(cartRepository.save(any())).thenReturn(cart);

        //When
        cart.getProducts().add(product);
        Cart updatedCart = cartRepository.save(cart);

        //Then
        assertEquals(1, updatedCart.getProducts().size());
        assertEquals("Monitor", updatedCart.getProducts().get(0).getName());

    }

    @Test
    void shouldDeleteCart(){

        //Given
        Cart cart = Cart.builder()
                .id(2l)
                .build();
        when(cartRepository.findById(2L)).thenReturn(Optional.of(cart));

        //When
        cartRepository.deleteById(2L);

        //Then
        when(cartRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Cart> deletedCart = cartRepository.findById(2L);
        assertFalse(deletedCart.isPresent());

    }




}
