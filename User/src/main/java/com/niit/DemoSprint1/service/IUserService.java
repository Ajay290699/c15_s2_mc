package com.niit.DemoSprint1.service;

import com.niit.DemoSprint1.domain.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user);
    public List<User> getAllUser();
    public String deleteUser(String email);
    public User updateUser(User user);
    public List<User> getAllUserByFirstName(String firstName);

    public User loginCheck(String email, String password);
}
