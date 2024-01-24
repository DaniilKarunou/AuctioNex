package com.auctionex.exception;

import com.auctionex.dto.Message;
import com.auctionex.exception.auth.UserAlreadyExistsException;
import com.auctionex.exception.auth.UserBadCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Message> handleEmailAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new Message(ex.getMessage()));
    }

    @ExceptionHandler(UserBadCredentialsException.class)
    public ResponseEntity<Message> handleUserBadCredentialsException(UserBadCredentialsException ex) {
        return ResponseEntity.badRequest().body(new Message(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Message> handleRuntimeException(RuntimeException ex) {
        log.error("Exception caught: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Something went wrong on the server"));
    }
}

