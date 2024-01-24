package com.auctionex.entity;

import com.auctionex.entity.auth.Role;
import lombok.*;
import org.hibernate.annotations.Check;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Check(constraints = "adres_mail LIKE '%@%'")
@Table(name = "users")
public class User {

    @Id
    private String login;

    private String password;

    private String name;

    private String surname;

    private String residenceAddress;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "login"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String name, String surname, String residenceAddress, String email, Set<Role> roles) {
        this.login = email.split("@")[0];
        this.name = name;
        this.surname = surname;
        this.residenceAddress = residenceAddress;
        this.email = email;
        this.roles = roles;
    }
}
