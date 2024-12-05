package com.shop.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.auth.LoginRequest;
import com.shop.dto.user.UserDTO;
import com.shop.service.interfaces.UserService;
import com.shop.validation.UserValidationService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing Category entities by the admin.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/auth")
@AllArgsConstructor
@Log4j2
public class AuthController {

    private final UserValidationService userValidationService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername  (),
        loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO userDTO) {
        log.info("UserDTO: " + userDTO.toString());
        if (userValidationService.isUsernameTaken(userDTO.getUsername())) {
            log.info("Username is already taken");
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        if (!userValidationService.doPasswordsMatch(userDTO.getPassword(), userDTO.getRepeatPassword())) {
            log.info("Passwords do not match");
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        userService.addUser(userDTO);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);

    }

}
