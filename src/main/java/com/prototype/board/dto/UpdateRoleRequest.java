package com.prototype.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRoleRequest {
    @NotBlank(message = "user name이 비어있습니다.")
    private String loginId;

    @NotNull(message = "role이 지정되지 않았습니다.")
    private int role;
}
