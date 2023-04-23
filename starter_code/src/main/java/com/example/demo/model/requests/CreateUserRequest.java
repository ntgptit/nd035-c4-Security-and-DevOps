package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty
    @Getter
    private String username;

    @JsonProperty
    @Getter
    private String password;

    @JsonProperty
    @Getter
    private String confirmPassword;

}
