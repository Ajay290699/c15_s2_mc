package com.niit.DemoSprint1.repository;

import com.niit.DemoSprint1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, String> {

    public List<User> findByfirstName(String firstName);
}
