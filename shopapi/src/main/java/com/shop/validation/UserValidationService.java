package com.shop.validation;

import org.springframework.stereotype.Service;

import com.shop.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUserName(username).isPresent();
    }

    public boolean doPasswordsMatch(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

}