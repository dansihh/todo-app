package com.project.todo_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({ "/todos", "/" })
    public String todosPage() {
        return "todos";
    }
}