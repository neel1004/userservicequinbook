package com.example.userService_quinBook.Services.Impl;

import com.example.userService_quinBook.Dto.ActivityDto;
import com.example.userService_quinBook.Entity.Activity;
import com.example.userService_quinBook.Repository.ActivityRepository;
import com.example.userService_quinBook.Services.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {


    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity addActivity(ActivityDto activityDto) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDto, activity);
        activity.setId(UUID.randomUUID().toString());
        activity.setDateUpdated(new Date());
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> getActivityById(String userId) {
        return activityRepository.findByUserId(userId);
    }
}

