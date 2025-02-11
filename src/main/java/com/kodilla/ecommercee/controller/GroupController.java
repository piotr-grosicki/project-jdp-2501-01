package com.kodilla.ecommercee.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public List<String> getGroupsList() {
        return List.of("Electronics", "Books", "Clothing");
    }

    @GetMapping("/{groupId}")
    public String getGroup(@PathVariable String groupId) {
        return "Twoja grupa to: " + groupId;
    }

    @PostMapping
    public String createGroup(@RequestBody String group) {
        return "Grupa '" + group + "' została utworzona";
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable String groupId) {
        return "Grupa o ID " + groupId + " została usunięta";
    }

    @PutMapping("/{groupId}")
    public String updateGroup(@PathVariable String groupId, @RequestBody String group) {
        return "Grupa o ID " + groupId + " została zaktualizowana na '" + group + "'";
    }
}
