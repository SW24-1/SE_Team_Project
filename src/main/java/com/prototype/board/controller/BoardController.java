package com.prototype.board.controller;

import com.prototype.board.dto.Issue;
import com.prototype.board.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final IssueService issueService;

    @GetMapping("/bug_search")
    public String bugSearch(Model model) {
        List<Issue> issues = issueService.findAll();
        model.addAttribute("issue", issues);
        return "bug_search";
    }

    @GetMapping("/bug_details/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        Issue detail = issueService.findById(id);
        model.addAttribute("detail", detail);
        return "bug_details";
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
