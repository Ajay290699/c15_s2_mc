package com.niit.DemoSprint1.service;

import com.niit.DemoSprint1.domain.User;
import com.niit.DemoSprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public User addUser(User user) {
        if (iUserRepository.findById(user.getEmail()).isEmpty()) {
            return iUserRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return iUserRepository.findAll();
    }

    @Override
    public String deleteUser(String email) {
        if (iUserRepository.findById(email).isEmpty()){
            return "User does not exist";
        }
        iUserRepository.deleteById(email);
        return "User deleted Successfully";
    }

    @Override
    public User updateUser(User user) {
        if (iUserRepository.findById(user.getEmail()).isEmpty()){
            return null;
        }
        User temp = iUserRepository.findById(user.getEmail()).get();
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        temp.setPassword(user.getPassword());
        return iUserRepository.save(temp);
    }

    @Override
    public List<User> getAllUserByFirstName(String firstName) {
        return iUserRepository.findByfirstName(firstName);
    }

    @Override
    public User loginCheck(String email, String password) {
        //check user is present or not
        if (iUserRepository.findById(email).isPresent()){
            //fetch user object by email
            User user = iUserRepository.findById(email).get();
            //check passsword
            if (user.getPassword().equals(password)){
                //valid user
                return user;
            }else {
                //invalid user
                return null;
            }
        }else {
            //if user does not exist
            return null;
        }
    }
}
