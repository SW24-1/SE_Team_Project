package com.prototype.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index() {
        System.out.println("HomeController.index");
        return "index";
    }
}