package com.niit.DemoSprint1.controller;

import com.niit.DemoSprint1.domain.User;
import com.niit.DemoSprint1.service.ISecurityTokenGenerator;
import com.niit.DemoSprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    IUserService iUserService;

    ISecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(ISecurityTokenGenerator securityTokenGenerator, IUserService userService) {
        this.securityTokenGenerator = securityTokenGenerator;
        this.iUserService= userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(iUserService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        return new ResponseEntity<>(iUserService.deleteUser(email), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return new ResponseEntity<>(iUserService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping("/user/{firstName}")
    public ResponseEntity<?> getAllUserWithFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(iUserService.getAllUserByFirstName(firstName), HttpStatus.FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user){
        User result = iUserService.loginCheck(user.getEmail(), user.getPassword());
        if (result != null){
            //valid user
            //genrate token
            Map<String, String> map= securityTokenGenerator.tokenGenrator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }else {
            //invalid user or user does not exist
            return new ResponseEntity<>("invalid user or user does not exist",HttpStatus.NOT_FOUND);
        }
    }
}
