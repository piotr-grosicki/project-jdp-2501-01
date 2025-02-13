package com.kodilla.ecommercee.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGroupsList_ShouldReturnListOfGroups() throws Exception {
        mockMvc.perform(get("/v1/groups"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Electronics\", \"Books\", \"Clothing\"]"));
    }

    @Test
    void getGroup() throws Exception {
        //Given
        String groupId = "123";

        //When
        ResultActions result = mockMvc.perform(get("/v1/groups/" + groupId));

        //Then
        result.andExpect(status().isOk())
                .andExpect(content().string("Twoja grupa to: " + groupId));
    }

    @Test
    void createGroup() throws Exception {
        //Given
        String newGroup = "Sport";

        //When
        ResultActions result = mockMvc.perform(post("/v1/groups")
                .contentType(MediaType.TEXT_PLAIN)
                        .content(newGroup));

        //Then
        result.andExpect(status().isOk())
                .andExpect(content().string("Grupa '" + newGroup + "' została utworzona"));
    }

    @Test
    void deleteGroup() throws Exception {
        //Given
        String groupId = "123";

        //When
        ResultActions result = mockMvc.perform(delete("/v1/groups/{groupId}", groupId));

        //Then
        result.andExpect(status().isOk())
                .andExpect(content().string("Grupa o ID " + groupId + " została usunięta"));
    }

    @Test
    void updateGroup() throws Exception {

        //Given
        String groupId = "123";
        String updatedGroup = "Updated Group";

        //When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/groups/{groupId}", groupId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(updatedGroup));

        //Then
        result.andExpect(status().isOk())
                .andExpect(content().string("Grupa o ID " + groupId + " została zaktualizowana na '" + updatedGroup + "'"));
    }
}