package com.prototype.board.service;

import com.prototype.board.dto.Comment;
import com.prototype.board.dto.CommentRequest;
import com.prototype.board.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void testComment() {
        String username = "testUser";
        Long issueId = 1L;
        CommentRequest req = new CommentRequest();
        req.setContext("This is a test comment");

        Comment comment = Comment.builder()
                .id(1L)
                .issueId(issueId)
                .author(username)
                .context(req.getContext())
                .createAt(LocalDateTime.now())
                .build();

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        commentService.comment(req, username, issueId);

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testFindByIssueId() {
        Long issueId = 1L;

        Comment comment1 = Comment.builder()
                .id(1L)
                .issueId(issueId)
                .author("user1")
                .context("Comment 1")
                .createAt(LocalDateTime.now())
                .build();

        Comment comment2 = Comment.builder()
                .id(2L)
                .issueId(issueId)
                .author("user2")
                .context("Comment 2")
                .createAt(LocalDateTime.now())
                .build();

        List<Comment> comments = List.of(comment1, comment2);

        when(commentRepository.findByIssueId(issueId)).thenReturn(comments);

        List<Comment> foundComments = commentService.findByIssueId(issueId);

        assertEquals(2, foundComments.size());
        assertEquals("Comment 1", foundComments.get(0).getContext());
        assertEquals("Comment 2", foundComments.get(1).getContext());
        verify(commentRepository, times(1)).findByIssueId(issueId);
    }
}
