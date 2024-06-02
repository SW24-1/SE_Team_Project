package com.prototype.board.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Status status;
    private Priority priority;
    private String reporter;
    private String handler;
    private String component;
    private LocalDateTime createAt;
    private LocalDateTime closeAt;
}
