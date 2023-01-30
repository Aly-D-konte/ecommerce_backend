package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.models.Notification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class NotificationController {


//    @PostMapping
//
//    public void sendNotification(@RequestBody Notification request) {
//        notificationService.sendNotification(request.getUserId(), request.getMessage());
//    }

}
