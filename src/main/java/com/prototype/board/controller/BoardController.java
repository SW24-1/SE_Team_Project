package com.prototype.board.controller;

import com.prototype.board.dto.Comment;
import com.prototype.board.dto.CommentRequest;
import com.prototype.board.dto.Issue;
import com.prototype.board.dto.User;
import com.prototype.board.service.CommentService;
import com.prototype.board.service.IssueService;
import com.prototype.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final IssueService issueService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/bug_search")
    public String bugSearch(Model model) {
        List<Issue> issues = issueService.findAll();
        model.addAttribute("issue", issues);
        return "bug_search";
    }

    @GetMapping("/bug_details/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        Issue detail = issueService.findById(id);
        List<Comment> comments =   commentService.findByIssueId(id);
        model.addAttribute("detail", detail);
        model.addAttribute("comments", comments);
        model.addAttribute("commentRequest", new CommentRequest());
        return "bug_details";
    }

    @PostMapping("/bug_details/{no}")
    public String postComment(@CookieValue(name = "userId", required = false) Long userId, @Valid @ModelAttribute CommentRequest commentRequest, @PathVariable("no") Long id, Model model) {
        User loginUser = userService.getLoginUserById(userId);
        commentService.comment(commentRequest, loginUser.getNickname(), id);
        return "redirect:/bug_details/{no}";
    }

    @GetMapping("/wiki/document_create")
    public String documentCreate(Model model) {
        return "wiki/document_create"; // 수정된 경로
    }

    @GetMapping("/wiki/edit_document")
    public String editDocument(Model model) {
        return "wiki/edit_document"; // 수정된 경로
    }
}
