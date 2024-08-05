package org.example.authenticationservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsResponse {
    private String password;
    private List<String> roles;
}
