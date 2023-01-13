package com.niit.C13_s5_mc.proxy;


import com.niit.C13_s5_mc.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "User-service", url = "localhost:8085")
public interface TrackProxy {

    @PostMapping("api/v1/register")
    public ResponseEntity<?> addUser(@RequestBody User user);
}
