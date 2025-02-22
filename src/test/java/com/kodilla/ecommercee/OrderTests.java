package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Calendar;
import java.util.Optional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class OrderTests {

    @MockBean
    private OrderRepository orderRepository;

    private final User user = new User();
    private final Cart cart = new Cart();

    @Test
    void shouldCreateOrder() throws Exception {

        //Given
        Calendar calendar = Calendar.getInstance();
        Order order = Order.builder()
                .user(user)
                .cart(cart)
                .status("done")
                .createdAt(calendar.getTime())
                .build();

        when(orderRepository.save(any())).thenReturn(order);

        //When
        Order savedOrder = orderRepository.save(order);

        //Then
        assertEquals(user, savedOrder.getUser());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shouldFindOrder() {
        // Given
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setUser(user);
        order.setCart(cart);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // When
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        // Then
        assertTrue(foundOrder.isPresent());
        assertEquals(orderId, foundOrder.get().getId());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void shouldUpdateOrder() {
        // Given
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setStatus("oczekujące");

        Order updatedOrder = new Order();
        updatedOrder.setId(orderId);
        updatedOrder.setStatus("wysłane");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any())).thenReturn(updatedOrder);

        // When
        Order savedOrder = orderRepository.save(updatedOrder);

        // Then
        assertEquals("wysłane", savedOrder.getStatus());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shouldDeleteOrder() {
        // Given
        Long orderId = 1L;
        doNothing().when(orderRepository).deleteById(orderId);

        // When
        orderRepository.deleteById(orderId);

        // Then
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}