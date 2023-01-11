package com.niit.DemoSprint1.service;

import com.niit.DemoSprint1.domain.User;

import java.util.Map;

public interface ISecurityTokenGenerator {

    public Map<String, String> tokenGenrator(User user);
}
