package com.prototype.board.service;

import com.prototype.board.dto.Issue;
import com.prototype.board.dto.IssueRequest;
import com.prototype.board.repository.IssueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;

    public void issue(IssueRequest req, String username) {
        issueRepository.save(req.toEntity(username));
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    public Issue findById(Long id) {
        Optional<Issue> issueOptional = issueRepository.findById(id);
        Issue issue = issueOptional.get();

        return issue;
    }
}
