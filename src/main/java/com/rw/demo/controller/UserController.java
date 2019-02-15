package com.rw.demo.controller;

import com.rw.demo.domain.User;
import com.rw.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired //@Autowired 可写可不写
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name) {//通过request请求参数
        User user = new User();

        user.setName(name);

        if (userRepository.save(user)) {
            System.out.printf("用户对象 :%s 保存成功 \n", user);
        }

        return user;
    }

}
