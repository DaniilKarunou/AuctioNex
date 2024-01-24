package com.auctionex.service.impl;

import com.auctionex.entity.User;
import com.auctionex.exception.auth.UserNotFoundException;
import com.auctionex.repository.UserRepository;
import com.auctionex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServicelmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(login);
        }
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> getAllUsersPaging(Integer pageNr, Integer howManyOnPage) {
        return userRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

    @Override
    public void deleteUser(String login) {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(login);
        }
        userRepository.delete(optionalUser.get());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(email);
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        userRepository.delete(optionalUser.get());
    }
}
