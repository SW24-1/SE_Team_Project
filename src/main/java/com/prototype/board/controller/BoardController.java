package com.prototype.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/bug_search")
    public String bugSearch(Model model) {
        return "bug_search";
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
