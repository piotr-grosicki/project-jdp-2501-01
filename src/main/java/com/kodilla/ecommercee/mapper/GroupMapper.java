package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(group.getId(), group.getName());
    }

    public Group mapToGroup(GroupDto groupDto) {
        return new Group(groupDto.getId(), groupDto.getName());
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}