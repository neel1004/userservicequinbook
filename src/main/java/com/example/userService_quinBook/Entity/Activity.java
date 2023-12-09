package com.example.userService_quinBook.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String userId;
    private String friendUserId;
    private Date dateUpdated;
    private String type;


}

