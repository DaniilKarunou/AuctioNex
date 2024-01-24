package com.auctionex.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String name;
    private String surname;
    private String residenceAddress;
    private String email;
    private String password;
}
