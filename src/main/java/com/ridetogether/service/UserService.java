
package com.ridetogether.service;

import com.ridetogether.entity.User;
import com.ridetogether.repository.UserRepository;
import com.ridetogether.exception.DuplicateResourceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("Duplicate email found!");
            throw new DuplicateResourceException("Email already registered");
        }

        if (userRepository.existsByPhone(user.getPhone())) {
            System.out.println("Duplicate phone found!");
            throw new DuplicateResourceException("Phone number already registered");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("Duplicate email found!");
            throw new DuplicateResourceException("Email already registered");
        }

        if (userRepository.existsByPhone(user.getPhone())&&userRepository.existsByEmail(user.getEmail())) {
            System.out.println("Duplicate Email & phone found!");
            throw new DuplicateResourceException("Email & Phone number already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
