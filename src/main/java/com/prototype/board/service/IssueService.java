package com.prototype.board.service;

import com.prototype.board.dto.IssueRequest;
import com.prototype.board.repository.IssueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;

    public void issue(IssueRequest req, String username) {
        issueRepository.save(req.toEntity(username));
    }
}
