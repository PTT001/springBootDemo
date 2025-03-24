package com.example.springbootdemo.service;

import com.example.springbootdemo.dto.UserLoginRequest;
import com.example.springbootdemo.dto.UserRegisterRequest;
import com.example.springbootdemo.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
