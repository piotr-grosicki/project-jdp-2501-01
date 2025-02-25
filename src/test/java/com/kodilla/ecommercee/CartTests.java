package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CartTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void shouldCreateCart(){

        //Given
        User user = User.builder()
                .id(1l)
                .username("jan123")
                .email("jan123@email.com")
                .password("password1")
                .isBlocked(false)
                .build();
        user = userRepository.save(user);


        Cart cart = Cart.builder()
                .user(user)
                .build();

        //When

        Cart savedCart = cartRepository.save(cart);

        //Then
        assertTrue(cartRepository.findById(savedCart.getId()).isPresent());

    }

    @Test
    void shouldFindCart(){

        //Given
        User user = User.builder()
                .id(1l)
                .username("anna456")
                .email("anna456@email.com")
                .password("password2")
                .isBlocked(false)
                .build();
        user = userRepository.save(user);

        Cart cart = Cart.builder()
                .user(user)
                .build();
        cart = cartRepository.save(cart);

        //When
        Optional<Cart> foundCart = cartRepository.findById(cart.getId());

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
                .email("jan123@email.com")
                .password("password1")
                .isBlocked(false)
                .build();
        user = userRepository.save(user);

        Group group = Group.builder()
                .name("Group 1")
                .products(new ArrayList<>())
                .build();

        group = groupRepository.save(group);

        Product product = Product.builder()
                .name("Monitor")
                .price(1500.00)
                .description("Monitor description")
                .stockQuantity(10)
                .group(group)
                .build();
        product = productRepository.save(product);

        Cart cart = Cart.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();
        cart = cartRepository.save(cart);

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
        User user = User.builder()
                .id(1l)
                .username("anna456")
                .email("anna456@email.com")
                .password("password2")
                .isBlocked(false)
                .build();
        user = userRepository.save(user);

        Cart cart = Cart.builder()
                .user(user)
                .build();
        cart = cartRepository.save(cart);

        Long cartId = cart.getId();

        //When
        cartRepository.deleteById(cartId);

        //Then
        Optional<Cart> deletedCart = cartRepository.findById(cartId);
        assertFalse(deletedCart.isPresent());

    }

}
