package com.prototype.board.dto;

import com.prototype.board.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class IssueRequest {
    @NotBlank(message = "제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "콘텐츠가 비어있습니다.")
    private String component;

    @NotNull(message = "중요도가 비어있습니다.")
    private int priority;

    public Issue toEntity(String userId) {
        Priority[] p = Priority.values();
        return Issue.builder()
                .title(this.title)
                .status(Status.NEW)
                .priority(p[this.priority])
                .reporter(userId)
                .handler(null)
                .createAt(LocalDateTime.now())
                .closeAt(null)
                .build();
    }
}
