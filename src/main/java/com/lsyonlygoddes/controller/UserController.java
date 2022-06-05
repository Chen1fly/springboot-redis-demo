package com.lsyonlygoddes.controller;

import com.lsyonlygoddes.entity.Chen;
import com.lsyonlygoddes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lsyonlygoddes
 * @time 2022/03/29 20:20
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/list")
    public List<Chen> list() {
        return userService.findAll();
    }
}
