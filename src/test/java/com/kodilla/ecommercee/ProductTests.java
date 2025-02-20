package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
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
public class ProductTests {

    @MockBean private ProductRepository productRepository;



@Test
void shouldCreateProduct() {
   //Given
    Product product = Product.builder()
            .name("Tv")
            .price(6200.0)
            .description("Smart Tv")
            .stockQuantity(10)
            .build();

    when(productRepository.save(any())).thenReturn(product);

    //When
    Product savedProduct = productRepository.save(product);

    //Then
    assertEquals("Tv", savedProduct.getName());
    verify(productRepository, times(1)).save(any());
}
@Test
void shouldFindProduct() {
    //Given
    Product product = Product.builder()
            .name("Smartphone")
            .price(1200.0)
            .description("New Smartphone")
            .stockQuantity(10)
            .build();
   when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    //When
    Optional<Product> foundProduct = productRepository.findById(1L);

    //Then
    assertTrue(foundProduct.isPresent());
    assertEquals("Smartphone", foundProduct.get().getName());
}


@Test
void shouldUpdateProduct() {
    //Given
    Product product = Product.builder()
            .name("PC")
            .price(3200.0)
            .description("Best PC")
            .stockQuantity(10)
            .build();
    when(productRepository.save(any())).thenReturn(product);

    //When
    product = productRepository.save(product);
    product.setPrice(3099.9);
    Product updatedProduct = productRepository.save(product);

    //Then
    assertEquals(3099.9, updatedProduct.getPrice());
}

@Test
    void shouldDeleteProduct() {
    //Given
    Product product = Product.builder()
            .id(2L)
            .name("Mouse")
            .price(50.0)
            .description("Gaming mouse")
            .stockQuantity(30)
            .build();

    when((productRepository.findById(2L))).thenReturn(Optional.of(product));

    //When
    productRepository.deleteById(2L);

    //Then
    when(productRepository.findById(2L)).thenReturn(Optional.empty());
    Optional<Product> deletedProduct = productRepository.findById(2L);
    assertFalse(deletedProduct.isPresent());
    }
}