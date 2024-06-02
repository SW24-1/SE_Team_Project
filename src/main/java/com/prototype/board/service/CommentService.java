package com.prototype.board.service;

import com.prototype.board.dto.Comment;
import com.prototype.board.dto.CommentRequest;
import com.prototype.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void comment(CommentRequest req, String username, Long issue_id) {
        commentRepository.save(req.toEntity(username, issue_id));
    }

    public List<Comment> findByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }


}