package com.prototype.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.spring6.processor.SpringUErrorsTagProcessor;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    @NotNull(message = "role을 지정해주세요.")
    private int role;

    // 비밀번호 암호화 X
    public User toEntity() {
        UserRole[] u = UserRole.values();
        // System.out.println(this.passwordCheck);
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(u[this.role])
                .build();
    }
}