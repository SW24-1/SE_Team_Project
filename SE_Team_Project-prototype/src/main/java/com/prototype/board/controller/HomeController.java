package com.prototype.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/dashboard","settings","/wiki","/bug_submit","/bug_search"})
    public String dashboard(Model model){
        return "dashboard";
    }
    @GetMapping({"/settings","/dashboard","/bug_submit","/bug_search"})
    public String setting(Model model) {
        return "settings";
    }
    @GetMapping({"/dashboard"})
    public String wiki(Model model) {
        return "wiki";
    }
    @GetMapping({"/register","/dashboard","/list"})
    public String index(Model model) {
        return "index"; //
    }
    @GetMapping({"/list","/index"})
    public String register(Model model){
        return "register";
    }

    @GetMapping({"/register","/index"})
    public String list(Model model){
        return "list";
    }



}
