package com.prototype.board.controller;

import com.prototype.board.dto.IssueRequest;
import com.prototype.board.dto.User;
import com.prototype.board.dto.UserRole;
import com.prototype.board.service.IssueService;
import com.prototype.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;
    private final UserService userService;

    @GetMapping("/bug_submit")
    public String bugSubmitPage(@CookieValue(name = "userId", required = false) Long userId, Model model) {
        User loginUser = userService.getLoginUserById(userId);
        if(loginUser.getRole() != UserRole.TESTER) {
            model.addAttribute("message", "tester만 접근 권한이 있습니다.");
            model.addAttribute("searchUrl", "/dashboard");
            return "message";
        }
        model.addAttribute("issueRequest", new IssueRequest());
        return "bug_submit";
    }

    @PostMapping("/bug_submit")
    public String bugSubmit(@CookieValue(name = "userId", required = false) Long userId, @Valid @ModelAttribute IssueRequest issueRequest, BindingResult bindingResult, Model model) {
        User loginUser = userService.getLoginUserById(userId);
        issueService.issue(issueRequest, loginUser.getNickname());
        return "redirect:/bug_search";
    }
}