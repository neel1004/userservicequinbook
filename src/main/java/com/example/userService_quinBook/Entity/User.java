package com.example.userService_quinBook.Entity;

import com.example.userService_quinBook.Dto.Request;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String userEmail;
    private String userName;
    private String userBio;
    private Date userCreated;
    private String userProfilePic;
    private boolean userIsPrivate;
    private String userAccountType;
    private List<String> userFriends = new ArrayList<>();
    private List<String> sentRequest = new ArrayList<>();
    private List<String> receiveRequest = new ArrayList<>();
}
