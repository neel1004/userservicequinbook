package com.example.userService_quinBook.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userEmail;
    private String userName;
    private String userBio;
    private String userProfilePic;
    private boolean userIsPrivate;
    private String userAccountType;
}
