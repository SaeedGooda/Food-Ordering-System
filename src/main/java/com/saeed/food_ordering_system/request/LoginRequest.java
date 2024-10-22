package com.saeed.food_ordering_system.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
