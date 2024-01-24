package com.auctionex.controller;

import com.auctionex.entity.User;
import com.auctionex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<User>> getUsersPage(
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        Iterable<User> usersPage = userService.getAllUsersPaging(page, pageSize);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{login}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> editUser(@PathVariable String login, @RequestBody User user) {
        if (userService.findUserByLogin(login) == null) {
            return ResponseEntity.noContent().build();
        }
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{login}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserByLogin(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("users/me/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> updateCurrentUser(@RequestBody User updatedUser) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        userService.saveUser(updatedUser);
        return ResponseEntity.ok("Current user updated successfully");
    }

    @DeleteMapping("/users/me/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> deleteCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("Current user deleted successfully");
    }
}