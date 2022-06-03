package com.mine.dojo.web;

import com.mine.dojo.model.User;
import com.mine.dojo.service.UserService;
import com.mine.dojo.web.dto.UserRequest;
import com.mine.dojo.web.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserWeb {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {

        var user = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
            .build();

        var userSaved = userService.save(user);

        var response = UserResponse.builder()
                .name(userSaved.getName())
                .password(userSaved.getPassword())
                .id(userSaved.getId())
            .build();

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
