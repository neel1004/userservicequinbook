package com.example.userService_quinBook.Controller;

import com.example.userService_quinBook.Dto.Response;
import com.example.userService_quinBook.Dto.UserDto;
import com.example.userService_quinBook.Entity.User;
import com.example.userService_quinBook.Services.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class UserController

{


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        for(User user: users){
            BeanUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        }

        return ResponseEntity.ok(users);
    }


    @PostMapping("/add-user")
    public ResponseEntity<Response> addUser(@RequestBody UserDto userDto) {
        Boolean isAdded = userService.addUser(userDto);
        Response response = new Response();
        if (isAdded) {
            response.setMessage("User Added");
            return ResponseEntity.ok(response);
        } else {
            response.setError("Failed to add user");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-user-by-id")
    public ResponseEntity<Response> getUserById(@RequestParam String userId) {
        Optional<User> user = userService.getUserById(userId);
        Response response = new Response();
        if (user.isPresent()) {
            response.setMessage("Got user");
            return ResponseEntity.ok(response);
        } else {
            response.setError("User Not found");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<Object> updateUser(@RequestParam("userId") String userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userDto);
        Response response = new Response();
        if (updatedUser != null) {
            response.setMessage("User Updated");
            return ResponseEntity.ok(response);
        } else {
            response.setError("Failed to update User");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/send-request")
    public ResponseEntity<Boolean> sendRequest(@RequestParam String userId, @RequestParam String requestedId){
        return  ResponseEntity.ok(userService.sendRequest(userId, requestedId));
    }

    @PutMapping("/accept-request")
    public ResponseEntity<Boolean> acceptRequest(@RequestParam String userId, @RequestParam String requestedId){
        return  ResponseEntity.ok(userService.acceptRequest(userId, requestedId));
    }

    @PutMapping("/reject-request")
    public ResponseEntity<Boolean> rejectRequest(@RequestParam String userId, @RequestParam String requestedId){
        return ResponseEntity.ok(userService.rejectRequest(userId, requestedId));

    }


}
