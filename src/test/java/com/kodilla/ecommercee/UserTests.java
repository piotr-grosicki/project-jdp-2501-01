package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testCreateUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setBlocked(false);

        // When
        userRepository.save(user);
        long userId = user.getId();

        // Then
        assertTrue(userRepository.findById(userId).isPresent());
    }

    @Test
    void testFindUser() {
        // Given
        User user = new User();
        user.setUsername("finduser");
        user.setEmail("find@example.com");
        user.setPassword("password123");
        user.setBlocked(false);

        // When
        userRepository.save(user);
        long userId = user.getId();
        Optional<User> optionalUser = userRepository.findById(userId);

        // Then
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void testUpdateUser() {
        // Given
        User user = new User();
        user.setUsername("updateuser");
        user.setEmail("update@example.com");
        user.setPassword("password123");
        user.setBlocked(false);

        // When
        userRepository.save(user);
        user = userRepository.findById(user.getId()).get();
        user.setEmail("updated@example.com");
        userRepository.save(user);

        // Then
        assertEquals("updated@example.com", userRepository.findById(user.getId()).get().getEmail());
    }

    @Test
    void testDeleteUser() {
        // Given
        User user = new User();
        user.setUsername("deleteuser");
        user.setEmail("delete@example.com");
        user.setPassword("password123");
        user.setBlocked(false);

        userRepository.save(user);
        long userId = user.getId();

        // When
        userRepository.deleteById(userId);

        // Then
        assertFalse(userRepository.findById(userId).isPresent());
    }

    @Test
    void testUserWithCartsAndOrders() {
        // Given
        User user = new User();
        user.setUsername("relationuser");
        user.setEmail("relation@example.com");
        user.setPassword("password123");
        user.setBlocked(false);

        Cart cart = new Cart();
        cart.setUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setCart(cart);
        order.setStatus("Pending");
        order.setCreatedAt(new Date());

        // When
        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);
        long userId = user.getId();
        long cartId = cart.getId();
        long orderId = order.getId();

        // Then
        assertTrue(userRepository.findById(userId).isPresent());
        assertTrue(cartRepository.findById(cartId).isPresent());
        assertTrue(orderRepository.findById(orderId).isPresent());
        assertEquals(cartId, orderRepository.findById(orderId).get().getCart().getId());
    }
}

