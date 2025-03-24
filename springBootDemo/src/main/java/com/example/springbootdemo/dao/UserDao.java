package com.example.springbootdemo.dao;

import com.example.springbootdemo.dto.UserRegisterRequest;
import com.example.springbootdemo.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
