package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Optional;

@Transactional
@SpringBootTest
public class OrderTests {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;


    @Test
    void createOrder() throws Exception {
        //Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        user.setBlocked(false);
        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);

        Calendar calendar = Calendar.getInstance();
        Order order = Order.builder()
                .user(user)
                .cart(cart)
                .status("done")
                .createdAt(calendar.getTime())
                .build();

        //When
        Order savedOrder = orderRepository.save(order);

        //Then
        assertTrue(orderRepository.findById(savedOrder.getId()).isPresent());
    }

    @Test
    void findOrder() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        user.setBlocked(false);
        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);

        Calendar calendar = Calendar.getInstance();
        Order order = Order.builder()
                .user(user)
                .cart(cart)
                .status("done")
                .createdAt(calendar.getTime())
                .build();
        // When
        long orderId = orderRepository.save(order).getId();
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        // Then
        assertTrue(foundOrder.isPresent());

    }

    @Test
    void updateOrder() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        user.setBlocked(false);
        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);

        Calendar calendar = Calendar.getInstance();
        Order order = Order.builder()
                .user(user)
                .cart(cart)
                .status("done")
                .createdAt(calendar.getTime())
                .build();

        // When
        Order savedOrder = orderRepository.save(order);
        order = orderRepository.findById(savedOrder.getId()).get();
        order.setStatus("pending");
        orderRepository.save(order);


        // Then
        assertEquals("pending", orderRepository.findById(savedOrder.getId()).get().getStatus());

    }

    @Test
    void deleteOrder() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        user.setBlocked(false);
        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);

        Calendar calendar = Calendar.getInstance();
        Order order = Order.builder()
                .user(user)
                .cart(cart)
                .status("done")
                .createdAt(calendar.getTime())
                .build();

        // When
        Order savedOrder = orderRepository.save(order);
        long orderId = savedOrder.getId();
        orderRepository.deleteById(orderId);

        // Then
        assertFalse(orderRepository.findById(orderId).isPresent());
    }
}