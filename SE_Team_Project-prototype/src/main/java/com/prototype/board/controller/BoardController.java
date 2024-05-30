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

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }
    @GetMapping("/dashboard")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "dashboard";
    }

    @GetMapping("/bug_summit")
    public String bugSummit(Model model) {
        return "bug_summit";
    }

    @GetMapping("/bug_search")
    public String bugSearch(Model model) {
        return "bug_search";
    }

    @GetMapping("/wiki")
    public String wiki(Model model) {
        return "wiki";
    }

    @GetMapping("/settings")
    public String setting(Model model) {
        return "settings";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "logout";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }

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

}