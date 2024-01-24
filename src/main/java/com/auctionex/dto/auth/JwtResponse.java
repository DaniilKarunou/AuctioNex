package com.auctionex.dto.auth;

import com.auctionex.entity.auth.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private Set<Role> roles;
}
