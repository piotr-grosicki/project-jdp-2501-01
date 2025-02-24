package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void shouldCreateProduct() {
        //Given

        Group group = Group.builder()
                .name("group")
                .products(new ArrayList<>())
                .build();

        Group savedGroup = groupRepository.save(group);

        Product product = Product.builder()
                .name("Tv")
                .price(6200.0)
                .description("Smart Tv")
                .stockQuantity(10)
                .group(savedGroup)
                .build();

        //When
        Product savedProduct = productRepository.save(product);

        //Then
        assertNotNull(product.getId());
        assertEquals("Tv", savedProduct.getName());
    }

    @Test
    void shouldFindProduct() {
        //Given
        Group group = Group.builder()
                .name("group")
                .products(new ArrayList<>())
                .build();

        Group savedGroup = groupRepository.save(group);

        Product product = Product.builder()
                .name("Smartphone")
                .price(1200.0)
                .description("New Smartphone")
                .stockQuantity(10)
                .group(savedGroup)
                .build();
        Product savedProduct = productRepository.save(product);

        //When
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        //Then
        assertTrue(foundProduct.isPresent());
        assertEquals("Smartphone", foundProduct.get().getName());
    }


    @Test
    void shouldUpdateProduct() {
        //Given
        Group group = Group.builder()
                .name("group")
                .products(new ArrayList<>())
                .build();

        Group savedGroup = groupRepository.save(group);

        Product product = Product.builder()
                .name("PC")
                .price(3200.0)
                .description("Best PC")
                .stockQuantity(10)
                .group(savedGroup)
                .build();

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
        Group group = Group.builder()
                .name("group")
                .products(new ArrayList<>())
                .build();

        Group savedGroup = groupRepository.save(group);

        Product product = Product.builder()
                .id(2L)
                .name("Mouse")
                .price(50.0)
                .description("Gaming mouse")
                .stockQuantity(30)
                .group(savedGroup)
                .build();

        product = productRepository.save(product);
        Long productId = product.getId();
        //When
        productRepository.deleteById(productId);

        //Then
        Optional<Product> deletedProduct = productRepository.findById(productId);
        assertFalse(deletedProduct.isPresent());
    }
}