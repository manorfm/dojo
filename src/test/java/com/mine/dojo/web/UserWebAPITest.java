package com.mine.dojo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mine.dojo.model.User;
import com.mine.dojo.service.UserService;
import com.mine.dojo.web.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.Is.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserWebAPITest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserWeb userWeb;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userWeb)
                .build();
    }

    @Test
    @DisplayName("Given an post request then should return user and a created code")
    public void testAnCreatedPostRequest() throws Exception {
        var name = "Some name";
        var password = "Some password";
        Long id = 9812378l;

        var user = User.builder()
                .name(name)
                .password(password)
                .id(id)
            .build();

        when(userService.save(any(User.class))).thenReturn(user);

        var request = new UserRequest();
        request.setName(name);
        request.setPassword(password);

        var json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", comparesEqualTo(id.intValue())))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.password", is(password)))
                .andDo(print());

        verify(userService, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userService);
    }
}
