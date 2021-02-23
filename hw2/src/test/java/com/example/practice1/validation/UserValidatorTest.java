package com.example.practice1.validation;


import com.example.practice1.exceptions.ConstraintViolationException;
import com.example.practice1.exceptions.LoginExistsException;
import com.example.practice1.model.NewUser;
import com.example.practice1.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {
    @Mock // створити об'єкт пустишку
    private UserRepository userRepository;
    @InjectMocks // створити реальний об'єкт і підставити в нього моки
    private UserValidator userValidator;

    @Test
    void shouldPassValidation() {
        userValidator.validateNewUser(
                NewUser.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build()
        );

        verify(userRepository).isLoginExists("new-logi n");

    }

    @Test
    void shouldThrowException_whenLoginExists() {

        when(userRepository.isLoginExists("new-login")).thenReturn(true);

        assertThatThrownBy(() -> userValidator.validateNewUser(
                NewUser.builder()
                        .login("new-login")
                        .fullName("fullname")
                        .password("pass")
                        .build()
        )).isInstanceOf(LoginExistsException.class)
                .hasMessage("Login new-login already taken");

    }

    @ParameterizedTest
    @MethodSource("testPasswordDataProvider")
    void shouldThrowException_whenPasswordIsInvalid(
            String password,
            final List<String> errors) {
        assertThatThrownBy(() -> userValidator.validateNewUser(
            NewUser.builder()
                    .login("new-login")
                    .fullName("fullname")
                    .password(password)
                    .build()
        )).isInstanceOfSatisfying(
                ConstraintViolationException.class,
                ex -> assertThat(ex.getErrors())
                        .containsExactlyInAnyOrderElementsOf(errors));
    }
    private static Stream<Arguments> testPasswordDataProvider(){
        return Stream.of(
                Arguments.of("&^%$^", List.of("Password doesn't match regex")),
                Arguments.of("qw", List.of("Password has invalid size")),
                Arguments.of("qwertyuio", List.of("Password has invalid size"))
        );
    }


}