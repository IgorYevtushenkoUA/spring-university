package com.example.practice1.model;

import lombok.Builder;
import lombok.Value;

@Value(staticConstructor = "of")
@Builder
public class User {

    private final String login;
    private final String password;
    private final String fullName;

}