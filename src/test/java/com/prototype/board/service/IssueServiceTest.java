package com.prototype.board.service;

import com.prototype.board.dto.Issue;
import com.prototype.board.dto.IssueRequest;
import com.prototype.board.dto.Priority;
import com.prototype.board.dto.Status;
import com.prototype.board.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @Test
    public void testIssue() {
        IssueRequest req = new IssueRequest();
        req.setTitle("Test Issue");
        req.setPriority(0);

        Issue issue = Issue.builder()
                .title("Test Issue")
                .status(Status.NEW)
                .priority(Priority.BLOCKER)
                .reporter("John Doe")
                .handler("Jane Smith")
                .component("Component1")
                .createAt(LocalDateTime.now())
                .build();

        when(issueRepository.save(any(Issue.class))).thenReturn(issue);

        issueService.issue(req, "John Doe");

        verify(issueRepository, times(1)).save(any(Issue.class));
    }

    @Test
    public void testFindAll() {
        Issue issue1 = new Issue();
        issue1.setTitle("Issue 1");
        Issue issue2 = new Issue();
        issue2.setTitle("Issue 2");

        when(issueRepository.findAll()).thenReturn(List.of(issue1, issue2));

        List<Issue> issues = issueService.findAll();

        assertNotNull(issues);
        assertEquals(2, issues.size());
        assertEquals("Issue 1", issues.get(0).getTitle());
        assertEquals("Issue 2", issues.get(1).getTitle());
    }

    @Test
    public void testFindById() {
        Long issueId = 1L;
        Issue issue = new Issue();
        issue.setId(issueId);
        issue.setTitle("Issue 1");

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(issue));

        Issue foundIssue = issueService.findById(issueId);

        assertNotNull(foundIssue);
        assertEquals("Issue 1", foundIssue.getTitle());
        verify(issueRepository, times(1)).findById(issueId);
    }

    @Test
    public void testExistsByTitle() {
        String title = "Existing Issue";

        when(issueRepository.existsByTitle(title)).thenReturn(true);

        boolean exists = issueRepository.existsByTitle(title);

        assertTrue(exists);
        verify(issueRepository, times(1)).existsByTitle(title);
    }

    @Test
    public void testFindByPriority() {
        Priority priority = Priority.BLOCKER;
        Issue issue = new Issue();
        issue.setPriority(priority);

        when(issueRepository.findByPriority(priority)).thenReturn(Optional.of(issue));

        Optional<Issue> foundIssue = issueRepository.findByPriority(priority);

        assertTrue(foundIssue.isPresent());
        assertEquals(priority, foundIssue.get().getPriority());
        verify(issueRepository, times(1)).findByPriority(priority);
    }
}
