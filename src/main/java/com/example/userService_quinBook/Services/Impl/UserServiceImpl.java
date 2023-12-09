package com.example.userService_quinBook.Services.Impl;
import com.example.userService_quinBook.Dto.UserDto;
import com.example.userService_quinBook.Entity.User;
import com.example.userService_quinBook.Repository.UserRepository;
import com.example.userService_quinBook.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public Boolean addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserId(UUID.randomUUID().toString());
        user.setUserCreated(new Date());
        User savedUser = userRepository.save(user);
        return Objects.nonNull(savedUser);

    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }


    @Override
    public boolean sendRequest(String userId, String requestedId) {
        try{

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> friendOptional = userRepository.findById(requestedId);

        if (userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();

            user.getSentRequest().add(requestedId);
            friend.getReceiveRequest().add(userId);

            userRepository.save(user);
            userRepository.save(friend);

            return true;
        }
        return false;}
        catch (Exception e) {
            throw new RuntimeException("Failed to send request: " + e.getMessage());
        }
    }


    @Override
    public boolean acceptRequest(String userId, String requestedId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<User> friendOptional = userRepository.findById(requestedId);

            if (userOptional.isPresent() && friendOptional.isPresent()) {
                User user = userOptional.get();
                User friend = friendOptional.get();

                user.getUserFriends().add(requestedId);
                friend.getUserFriends().add(userId);

                user.getSentRequest().remove(requestedId);
                friend.getReceiveRequest().remove(userId);

                userRepository.save(user);
                userRepository.save(friend);

                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept request: " + e.getMessage());
        }
    }



    @Override
    public boolean rejectRequest(String userId, String requestedId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<User> friendOptional = userRepository.findById(requestedId);

            if (userOptional.isPresent() && friendOptional.isPresent()) {
                User user = userOptional.get();
                User friend = friendOptional.get();

                user.getSentRequest().remove(requestedId);
                friend.getReceiveRequest().remove(userId);

                userRepository.save(user);
                userRepository.save(friend);

                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to reject request: " + e.getMessage());
        }
    }


    @Override
    public User updateUser(String userId, UserDto userDto) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                BeanUtils.copyProperties(userDto, user);

                return userRepository.save(user); // Save and return the updated user
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }


    }
}


