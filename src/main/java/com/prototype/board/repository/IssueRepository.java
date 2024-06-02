package com.prototype.board.repository;

import com.prototype.board.dto.Issue;
import com.prototype.board.dto.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    boolean existsByTitle(String title);
    Optional<Issue> findByPriority(Priority priority);
}
