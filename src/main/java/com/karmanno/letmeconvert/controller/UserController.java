package com.karmanno.letmeconvert.controller;

import com.karmanno.letmeconvert.model.User;
import com.karmanno.letmeconvert.payload.response.ProfileResponse;
import com.karmanno.letmeconvert.repository.UserRepository;
import com.karmanno.letmeconvert.security.CurrentUser;
import com.karmanno.letmeconvert.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ProfileResponse getProfile(@CurrentUser UserPrincipal currentUser) {
        List<String> strings = Arrays.asList("ads", "cx", "rte");
        User user = userRepository.findByUsernameOrEmail(currentUser.getUsername(), currentUser.getEmail()).get();
        return new ProfileResponse(currentUser.getUsername(), currentUser.getEmail(), user.getCreatedAt(), strings);
    }
}
