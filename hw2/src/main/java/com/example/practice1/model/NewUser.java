package com.example.practice1.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
public class NewUser {

    private final String login;
    private final String password;
    private final String fullName;

}
