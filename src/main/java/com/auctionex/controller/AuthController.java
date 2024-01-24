package com.auctionex.controller;

import com.auctionex.dto.Message;
import com.auctionex.dto.auth.JwtResponse;
import com.auctionex.dto.auth.LoginRequest;
import com.auctionex.dto.auth.SignupRequest;
import com.auctionex.entity.User;
import com.auctionex.entity.auth.Role;
import com.auctionex.exception.auth.UserAlreadyExistsException;
import com.auctionex.exception.auth.UserBadCredentialsException;
import com.auctionex.security.JwtTokenProvider;
import com.auctionex.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserDetailsService userDetailsService, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@RequestBody SignupRequest signupRequest) {
        // Check if the user with the provided email already exists
        if (userService.existsByEmail(signupRequest.getEmail())) {
            // Handle the case where the email is already taken
            throw new UserAlreadyExistsException(signupRequest.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        // Create a new user entity and set its properties
        User newUser = new User(
                signupRequest.getName(),
                signupRequest.getSurname(),
                signupRequest.getResidenceAddress(),
                signupRequest.getEmail(),
                roles
        );
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Save the new user
        userService.saveUser(newUser);

        // Authenticate the newly registered user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(signupRequest.getEmail());
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(token, roles));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new UserBadCredentialsException(loginRequest.getEmail());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(token, userService.getUserByEmail(loginRequest.getEmail()).getRoles()));
    }

    @PostMapping("/signout")
    public ResponseEntity<Message> signout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new Message("Logout successful"));
    }
}