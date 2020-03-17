package com.example;

import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot14DubboConsumerUserApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {

        userService.hello();
    }
}
