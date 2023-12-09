package com.example.userService_quinBook.Dto;

import lombok.Data;

@Data
public class Request {
    private String userId;
    private String friendId;
    private String status;
}
