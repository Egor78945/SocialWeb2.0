package org.example.authenticationservice.model.request;

import java.util.Objects;

public class AuthenticationRequestModel {
    private String email;
    private String password;

    public AuthenticationRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationRequestModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AuthenticationRequestModel that = (AuthenticationRequestModel) object;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
