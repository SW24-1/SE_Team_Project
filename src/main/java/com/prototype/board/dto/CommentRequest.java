package com.prototype.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "내용이 비어있습니다.")
    private String context;

    public Comment toEntity(String userId, Long issue_id) {
        return Comment.builder()
                .issueId(issue_id)
                .context(this.context)
                .author(userId)
                .createAt(LocalDateTime.now())
                .build();
    }
}
