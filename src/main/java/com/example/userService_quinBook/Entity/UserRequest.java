package com.example.userService_quinBook.Entity;

import lombok.Data;
@Data
public class UserRequest {

        private String userId;
        private String friendId;
        private enum Status {
                PENDING,
                FRIEND,
                NOT_FRIENDS
        }



}
