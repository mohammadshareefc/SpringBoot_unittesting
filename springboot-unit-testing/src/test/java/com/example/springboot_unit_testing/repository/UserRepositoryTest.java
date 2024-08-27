package com.example.springboot_unit_testing.repository;

import com.example.springboot_unit_testing.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_ShouldReturnSavedUser() {
        // Arrange
        User user = new User("John Doe", "john@example.com");

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void findById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User user = new User("John Doe", "john@example.com");
        userRepository.save(user);

        // Act
        Optional<User> foundUser = userRepository.findById(user.getId());

        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John Doe");
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenUserDoesNotExist() {
        // Act
        Optional<User> foundUser = userRepository.findById(1L);

        // Assert
        assertThat(foundUser).isEmpty();
    }
}
