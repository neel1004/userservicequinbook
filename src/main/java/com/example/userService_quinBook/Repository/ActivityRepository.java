package com.example.userService_quinBook.Repository;

import com.example.userService_quinBook.Entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    List<Activity> findByUserId(String userId);

}
