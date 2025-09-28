package com.project.todo_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hi semua!!!";
    }

}
