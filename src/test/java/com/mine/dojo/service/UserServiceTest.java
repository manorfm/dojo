package com.mine.dojo.service;

import com.mine.dojo.model.User;
import com.mine.dojo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Given an user when persist it then should return user different of null")
    public void testToPersistNotNull() {
        var name = "name";
        var password = "password";

        var user = User.builder()
                .name(name)
                .password(password)
                .build();

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        //assertNotNull(savedUser.getId());
    }

    @Test
    @DisplayName("Given an user then should persist it")
    public void testToPersist() {
        var name = "name";
        var password = "password";
        var someId = 16251651l;

        var user = User.builder()
                .name(name)
                .password(password)
                .build();

        var savedUser = User.builder()
                .name(name)
                .password(password)
                .id(someId)
                .build();

        when(userRepository.save(user)).thenReturn(savedUser);

        User userExpected = userService.save(user);

        assertEquals(name, userExpected.getName());
        assertEquals(password, userExpected.getPassword());
        assertEquals(someId, userExpected.getId());

        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }
}
