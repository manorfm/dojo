package com.mine.dojo.web;

import com.mine.dojo.model.User;
import com.mine.dojo.repository.UserRepository;
import com.mine.dojo.service.UserService;
import com.mine.dojo.web.dto.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class UserWebIntegrationTest {

    @Mock
    private UserRepository userRepository;

    @Spy @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserWeb userWeb;

    @Test
    @DisplayName("Given an request with data then should create and save an user")
    public void testToSaveUserByPostRequest() {
        var name = "Some name";
        var password = "Some password";

        var userSaved = User.builder()
                .name(name)
                .password(password)
                .id(19123l)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(userSaved);

        var userResponse = userWeb.save(new UserRequest()).getBody();

        assertNotNull(userResponse);
        assertEquals(name, userResponse.getName());
        assertEquals(password, userResponse.getPassword());
        assertNotNull(userResponse.getId());

        verify(userRepository, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }
}
