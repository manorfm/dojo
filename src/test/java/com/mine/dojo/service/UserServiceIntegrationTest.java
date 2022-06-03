package com.mine.dojo.service;


import com.mine.dojo.model.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Given an user then should persist it")
    public void testToPersistNotNull() {
        var user = User.builder()
                .name("some name")
                .password("some password")
                .build();

        var userExpected = userService.save(user);

        assertNotNull(userExpected);
        assertNotNull(userExpected.getId());
    }
}
