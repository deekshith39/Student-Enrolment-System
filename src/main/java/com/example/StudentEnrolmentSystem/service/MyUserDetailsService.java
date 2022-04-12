package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.model.MyUserDetails;
import com.example.StudentEnrolmentSystem.model.User;
import com.example.StudentEnrolmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).get();

        if(Objects.equals(user.getStatus(), "ACTIVE")) {
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User is unauthorized");
        }
    }
}
