package io.hjforever.springpb.controller;

import io.hjforever.springpb.user.UserRequest;
import io.hjforever.springpb.user.UserResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user/{id}")
    public UserResp user(@PathVariable Integer id) {
        // 模拟
        UserResp userResp = UserResp.newBuilder()
                .setUserId(18)
                .setAge(18)
                .setName("hjforever").build();
        return userResp;
    }

    @RequestMapping(value = "/user/query",method = RequestMethod.POST)
    public UserResp query(@RequestBody UserRequest userRequest) {
        logger.info("user request is : {} ", userRequest);
        // 模拟
        UserResp userResp = UserResp.newBuilder()
                .setUserId(userRequest.getUserId())
                .setAge(18).setName(userRequest.getName())
                .build();
        return userResp;

    }

}
