package io.hjforever.springboot.http2.controller;


import io.hjforever.springboot.http2.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/user/{id}")
    public User user(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("user_" + id);
        return user;
    }

}
