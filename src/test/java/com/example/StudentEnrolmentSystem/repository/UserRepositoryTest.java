package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void saveStudent() {
        User user = User.builder()
                .password(passwordEncoder.encode("12345"))
                .role("USER")
                .build();

        userRepository.save(user);
    }
}