package com.example.userService_quinBook.Dto;

import lombok.Data;

import java.util.Date;
@Data
public class ActivityDto {
    private String userId;
    private String friendUserId;
    private String type;
}
