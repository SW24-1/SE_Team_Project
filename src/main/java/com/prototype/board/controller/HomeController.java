package com.prototype.board.controller;

import com.prototype.board.dto.UpdateRoleRequest;
import com.prototype.board.dto.User;
import com.prototype.board.dto.UserRole;
import com.prototype.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        List<User> users = userService.findAll();
        if(loginUser == null) {
            return "redirect:/login";
        }
        if(loginUser.getRole() == UserRole.ADMIN) {
            model.addAttribute("user", users);
            model.addAttribute("updateRoleRequest", new UpdateRoleRequest());
            return "admin_page";
        }
        model.addAttribute("user", loginUser);
        System.out.println("login user role : " + loginUser.getRole());
        return "dashboard";
    }

    @PostMapping("/update_role")
    public String updateRole(@Valid UpdateRoleRequest updateRoleRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateRoleRequest", updateRoleRequest);
            return "dashboard"; // 오류가 발생한 경우 폼으로 돌아갑니다.
        }

        userService.updateRole(updateRoleRequest);
        return "redirect:/dashboard";
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
