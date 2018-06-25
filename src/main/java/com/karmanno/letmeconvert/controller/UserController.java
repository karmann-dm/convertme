package com.karmanno.letmeconvert.controller;

import com.karmanno.letmeconvert.model.User;
import com.karmanno.letmeconvert.payload.response.ProfileResponse;
import com.karmanno.letmeconvert.security.CurrentUser;
import com.karmanno.letmeconvert.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/profile")
    public ProfileResponse getProfile(@CurrentUser UserPrincipal currentUser) {
        return new ProfileResponse(currentUser.getUsername(), currentUser.getEmail(), Arrays.asList("ads", "cx", "rte"));
    }
}
