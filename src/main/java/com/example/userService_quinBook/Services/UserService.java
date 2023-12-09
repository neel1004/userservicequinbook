package com.example.userService_quinBook.Services;

import com.example.userService_quinBook.Dto.UserDto;
import com.example.userService_quinBook.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Boolean addUser(UserDto userDto);
    Optional<User> getUserById(String userId);
    User updateUser(String userId,UserDto userDto);

    boolean sendRequest(String userId, String requestedId);

    boolean acceptRequest(String userId, String requestedId);

    boolean rejectRequest(String userId, String requestedId);
}
