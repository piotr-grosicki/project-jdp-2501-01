package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        // Given
        User user = User.builder()
                .username("jan_nowak")
                .email("jan.nowak@example.com")
                .password("password123")
                .isBlocked(false)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertEquals("jan_nowak", savedUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldFindUser() {
        // Given
        User user = User.builder()
                .username("jan_nowak")
                .email("jan.nowak@example.com")
                .password("password456")
                .isBlocked(false)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        Optional<User> foundUser = userRepository.findById(1L);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("jan_nowak", foundUser.get().getUsername());
    }

    @Test
    void shouldUpdateUser() {
        // Given
        User user = User.builder()
                .username("mateusz")
                .email("mateusz@example.com")
                .password("oldPassword")
                .isBlocked(false)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        user = userRepository.save(user);

        user.setPassword("newPassword");
        user.setBlocked(true);
        User updatedUser = userRepository.save(user);

        // Then
        assertEquals("newPassword", updatedUser.getPassword());
        assertTrue(updatedUser.isBlocked());
    }

    @Test
    void shouldDeleteUser() {
        // Given
        User user = User.builder()
                .id(2L)
                .username("bob")
                .email("bob@example.com")
                .password("bobpassword")
                .isBlocked(false)
                .build();

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        // When
        userRepository.deleteById(2L);

        // Then
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<User> deletedUser = userRepository.findById(2L);
        assertFalse(deletedUser.isPresent());
    }
}