package org.example.authenticationservice.model.request;

import lombok.Data;

@Data
public class AuthenticationRequestModel {
    private String email;
    private String password;
}
