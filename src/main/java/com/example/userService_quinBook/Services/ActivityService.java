package com.example.userService_quinBook.Services;

import com.example.userService_quinBook.Dto.ActivityDto;
import com.example.userService_quinBook.Entity.Activity;

import java.util.List;

public interface ActivityService {

    Activity addActivity(ActivityDto activityDto);

    List<Activity> getActivityById(String userId);

}
