package com.prototype.board.service;

import com.prototype.board.dto.*;
import com.prototype.board.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCheckLoginIdDuplicate() {
        String loginId = "testUser";
        when(userRepository.existsByLoginId(loginId)).thenReturn(true);

        boolean isDuplicate = userService.checkLoginIdDuplicate(loginId);

        assertTrue(isDuplicate);
        verify(userRepository, times(1)).existsByLoginId(loginId);
    }

    @Test
    public void testCheckNicknameDuplicate() {
        String nickname = "testNick";
        when(userRepository.existsByNickname(nickname)).thenReturn(true);

        boolean isDuplicate = userService.checkNicknameDuplicate(nickname);

        assertTrue(isDuplicate);
        verify(userRepository, times(1)).existsByNickname(nickname);
    }

    @Test
    public void testJoin() {
        JoinRequest req = new JoinRequest();
        req.setLoginId("testUser");
        req.setPassword("password");
        req.setNickname("testNick");

        User user = User.builder()
                .loginId(req.getLoginId())
                .password(req.getPassword())
                .nickname(req.getNickname())
                .role(UserRole.DEV)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.join(req);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLogin() {
        LoginRequest req = new LoginRequest();
        req.setLoginId("testUser");
        req.setPassword("password");

        User user = User.builder()
                .loginId(req.getLoginId())
                .password(req.getPassword())
                .build();

        when(userRepository.findByLoginId(req.getLoginId())).thenReturn(Optional.of(user));

        User loggedInUser = userService.login(req);

        assertNotNull(loggedInUser);
        assertEquals(req.getLoginId(), loggedInUser.getLoginId());
        assertEquals(req.getPassword(), loggedInUser.getPassword());
    }

    @Test
    public void testLoginInvalidPassword() {
        LoginRequest req = new LoginRequest();
        req.setLoginId("testUser");
        req.setPassword("wrongPassword");

        User user = User.builder()
                .loginId(req.getLoginId())
                .password("correctPassword")
                .build();

        when(userRepository.findByLoginId(req.getLoginId())).thenReturn(Optional.of(user));

        User loggedInUser = userService.login(req);

        assertNull(loggedInUser);
    }

    @Test
    public void testLoginNonExistentUser() {
        LoginRequest req = new LoginRequest();
        req.setLoginId("nonExistentUser");
        req.setPassword("password");

        when(userRepository.findByLoginId(req.getLoginId())).thenReturn(Optional.empty());

        User loggedInUser = userService.login(req);

        assertNull(loggedInUser);
    }

    @Test
    public void testGetLoginUserById() {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .loginId("testUser")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getLoginUserById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetLoginUserByLoginId() {
        String loginId = "testUser";
        User user = User.builder()
                .loginId(loginId)
                .build();

        when(userRepository.findByLoginId(loginId)).thenReturn(Optional.of(user));

        User foundUser = userService.getLoginUserByLoginId(loginId);

        assertNotNull(foundUser);
        assertEquals(loginId, foundUser.getLoginId());
        verify(userRepository, times(1)).findByLoginId(loginId);
    }

    @Test
    public void testUpdateRole() {
        String loginId = "testUser";
        int newRole = UserRole.PL.ordinal();
        UpdateRoleRequest req = new UpdateRoleRequest();
        req.setLoginId(loginId);
        req.setRole(newRole);

        User user = User.builder()
                .loginId(loginId)
                .role(UserRole.DEV)
                .build();

        when(userRepository.findByLoginId(loginId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.updateRole(req);

        assertEquals(UserRole.PL, user.getRole());
        verify(userRepository, times(1)).save(user);
    }
}
