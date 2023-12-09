package com.example.userService_quinBook.Controller;

import com.example.userService_quinBook.Dto.ActivityDto;
import com.example.userService_quinBook.Dto.Response;
import com.example.userService_quinBook.Entity.Activity;
import com.example.userService_quinBook.Entity.User;
import com.example.userService_quinBook.Services.ActivityService;
import com.example.userService_quinBook.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-activity")
    public ResponseEntity<Response> addActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.addActivity(activityDto);
        Response response=new Response();
        if (activity.getId()!=null) {
            response.setMessage("Activity Added");
            return ResponseEntity.ok(response);
        } else {
            response.setError("Failed to add Activity");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-activity-by-id")
    public ResponseEntity<List<String>> getActivityById(@RequestParam String userId) {

        List<Activity> activities = activityService.getActivityById(userId);
        List<String> activityData = new ArrayList<>();

        for(Activity activity : activities){
            Optional<User> userOptional = userService.getUserById(activity.getFriendUserId());
            activityData.add(userOptional.get().getUserName() + " "+ activity.getType()+ "  on the post." );
        }

        return ResponseEntity.ok(activityData);
    }


}
