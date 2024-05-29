package com.prototype.board.controller;


import com.prototype.board.dto.BoardDTO;
import com.prototype.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // /10, /1
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // 조회수 처리
        boardService.updateHits(id);
        // 상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        return "detail";
    }

    @GetMapping({"/dashboard","/bug_submit"})
    public String bug_submit(Model model) {
        return "bug_submit";
    }
    @GetMapping({"/bug_search","/dashboard"})
    public String bug_search(Model model) {
        return "bug_search";
    }
    @GetMapping({"/wiki"})
    public String document_create(Model model) {
        return "document_create";
    }

    @GetMapping({"/wiki"})
    public String edit_document(Model model) {
        return "edit_document";
    }

}



