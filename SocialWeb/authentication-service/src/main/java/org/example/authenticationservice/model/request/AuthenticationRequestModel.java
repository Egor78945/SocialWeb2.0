package org.example.authenticationservice.model.request;

import lombok.Data;

@Data
public class AuthenticationRequestModel {
    private String email;
    private String password;

    public AuthenticationRequestModel() {
    }

    public AuthenticationRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
