package com.prototype.board.controller;

import com.prototype.board.dto.User;
import com.prototype.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    /*@GetMapping({"/home", "/login"})
    public String index(Model model) {
        return "index";
    }*/

    @GetMapping("/dashboard")
    public String dashboard(@CookieValue(name = "userId", required = false) Long userId, Model model){
        User loginUser = userService.getLoginUserById(userId);
        if(loginUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", loginUser);
        System.out.println("login user role : " + loginUser.getRole());
        return "dashboard";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "settings";
    }

    @GetMapping("/wiki")
    public String wiki(Model model) {
        return "wiki";
    }

    /*@GetMapping("/register")
    public String register(Model model){
        return "register";
    }*/

    @GetMapping("/list")
    public String list(Model model){
        return "list";
    }
}
