package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
