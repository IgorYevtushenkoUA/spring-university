package com.example.practice1.service;


import com.example.practice1.exceptions.LoginExistsException;
import com.example.practice1.exceptions.UserNotFoundException;
import com.example.practice1.model.NewUser;
import com.example.practice1.model.User;
import com.example.practice1.repository.UserRepository;
import com.example.practice1.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTest {

    @Autowired // щоби отримати реальний об'єкт
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    //    @MockBean
    @SpyBean
    private UserValidator userValidator;
    // мок нереальний об'єкт а заглушка

    @Test
    void shouldCreateValidUser_userValidInput() {
        userService.createNewUser(
                NewUser.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build()
        );

        verify(userValidator).validateNewUser(
                ArgumentMatchers.any());

        verify(userValidator).validateNewUser(
                NewUser.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build()
        );
        verify(userValidator).validateNewUser(
                argThat(user -> {
                    return user.getLogin().equals("new-login")
                            && user.getFullName().equals("fullname");

                })
        );

        assertThat(userRepository.getUserByLogin("new-login"))
                .isEqualTo(User.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build());
        // alternative
        assertThat(userRepository.getUserByLogin("new-login"))
                .returns("new-login", User::getLogin)
                .returns("pass", User::getPassword)
                .isEqualTo(User.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build());
    }


    @Test
    void shouldThrowException_whenValidUser_butLoginExists() {
        String existedLogin = "login1";

        assertThrows(LoginExistsException.class, () -> {
            userService.createNewUser(
                    NewUser.builder()
                            .login(existedLogin)
                            .fullName("fullname")
                            .password("pass")
                            .build()
            );
        });

    }

    @Test
    void shouldThrowException_whenNotExistedLogin() {
        String login = "prostoLogin";

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByLogin(login);
        });
    }

}