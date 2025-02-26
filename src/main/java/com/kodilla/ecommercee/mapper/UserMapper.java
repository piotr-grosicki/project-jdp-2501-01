package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto maptoUserDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isBlocked(),
                user.getCarts(),
                user.getOrders());
    }

    public User maptoUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getIsBlocked(),
                userDto.getCarts(),
                userDto.getOrders());
    }

    public List<UserDto> maptoUserDto(List<User> users) {
        return users.stream().map(this::maptoUserDto).collect(Collectors.toList());
    }
}
