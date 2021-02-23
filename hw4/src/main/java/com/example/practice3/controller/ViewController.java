package com.example.practice3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

}
