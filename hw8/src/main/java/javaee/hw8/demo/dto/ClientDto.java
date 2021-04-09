package javaee.hw8.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class ClientDto {

    @NotEmpty(message = "Name can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]{2,40}$", message = "Name has bad format")
    private String name;

    @NotEmpty(message = "Surname can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]{2,40}$", message = "Surname has bad format")
    private String surname;

    @NotEmpty(message = "PhoneNumber can't be empty")
    @Pattern(regexp = "^[-+0-9]{7,14}$", message = "Name has bad format")
    private String phoneNumber;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8, max=20, message = "Incorrect password length")
    public String password;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8, max=20, message = "Incorrect password length")
    private String password2;

}
