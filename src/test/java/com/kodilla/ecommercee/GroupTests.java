package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupTests {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testCreateGroup() {
        //Given
        Group group = new Group();
        group.setName("Group 1");

        //When
        groupRepository.save(group);
        long groupId = group.getId();

        //Then
        assertTrue(groupRepository.findById(groupId).isPresent());
    }

    @Test
    void testFindGroup() {
        //Given
        Group group = new Group();
        group.setName("Group 2");

        //When
        groupRepository.save(group);
        long groupId = group.getId();
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        //Then
        assertTrue(optionalGroup.isPresent());
    }

    @Test
    void testUpdateGroup() {
        //Given
        Group group = new Group();
        group.setName("Group 1");

        //When
        groupRepository.save(group);
        group = groupRepository.findById(group.getId()).get();
        group.setName("Updated Group 1");
        groupRepository.save(group);

        //Then
        assertEquals("Updated Group 1", groupRepository.findById(group.getId()).get().getName());
    }

    @Test
    void testDeleteGroup() {
        //Given
        Group group = new Group();
        group.setName("Group 1");

        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(100.0);
        product.setStockQuantity(15);

        group = groupRepository.save(group);
        product.setGroup(group);
        product = productRepository.save(product);
        group.setProducts(List.of(product));

        //When
        groupRepository.save(group);
        long groupId = group.getId();
        groupRepository.deleteById(groupId);

        //Then
        assertFalse(groupRepository.findById(groupId).isPresent());
        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

}