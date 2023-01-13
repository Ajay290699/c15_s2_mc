package com.niit.C13_s5_mc.repository;

import com.niit.C13_s5_mc.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
