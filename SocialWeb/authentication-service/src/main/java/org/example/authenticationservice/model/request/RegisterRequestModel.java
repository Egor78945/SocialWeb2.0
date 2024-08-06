package org.example.authenticationservice.model.request;

import lombok.Data;

@Data
public class RegisterRequestModel {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String city;
    private String password;
}
