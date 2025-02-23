package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupTests {

    @Autowired
    private GroupRepository groupRepository;

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

        //When
        groupRepository.save(group);
        long groupId = group.getId();
        groupRepository.deleteById(groupId);

        //Then
        assertFalse(groupRepository.findById(groupId).isPresent());
    }

}