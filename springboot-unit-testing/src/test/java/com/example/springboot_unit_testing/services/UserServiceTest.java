package com.example.springboot_unit_testing.services;

import com.example.springboot_unit_testing.exception.UserNotFoundException;
import com.example.springboot_unit_testing.model.User;
import com.example.springboot_unit_testing.repository.UserRepository;
import com.example.springboot_unit_testing.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllUsers_ShouldReturnAllUsers() {
        // Arrange
        User user1 = new User("John Doe", "john@example.com");
        User user2 = new User("Jane Doe", "jane@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertThat(users).hasSize(2);
        assertThat(users).contains(user1, user2);
    }

    @Test
    public void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        User user = new User("John Doe", "john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.getUserById(userId);

        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("John Doe");
    }

    @Test
    public void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });
    }
}
